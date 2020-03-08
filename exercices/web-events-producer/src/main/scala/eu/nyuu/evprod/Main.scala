package eu.nyuu.evprod

import java.util.Properties

import akka.actor.ActorSystem
import akka.stream.SystemMaterializer
import akka.stream.scaladsl.{Sink, Source}
import com.ovoenergy.kafka.serialization.circe._
import com.typesafe.scalalogging.Logger
import eu.nyuu.evprod.encoders._
import eu.nyuu.evprod.models.{Id, Metric, Visit}
import eu.nyuu.evprod.utils.Producer
import eu.nyuu.evprod.utils.Producer.KafkaProducerOps
import io.circe.generic.auto._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, RecordMetadata}
import org.apache.kafka.common.serialization.Serializer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Main extends App {
  val logger: Logger = Logger("main")

  val config = new Properties()
  config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:29092")
  config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer")
  config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer")

  implicit val visitKeySerializer: Serializer[Id[Visit]] = circeJsonSerializer[Id[Visit]]
  implicit val visitValueSerializer: Serializer[Visit] = circeJsonSerializer[Visit]
  implicit val metricKeySerializer: Serializer[Id[Metric]] = circeJsonSerializer[Id[Metric]]
  implicit val metricValueSerializer: Serializer[Metric] = circeJsonSerializer[Metric]

  val visitsProducer: KafkaProducer[Id[Visit], Visit] = Producer[Visit](config)
  val metricsProducer: KafkaProducer[Id[Metric], Metric] = Producer[Metric](config)

  implicit val system: ActorSystem = ActorSystem("system")
  implicit val materializer: SystemMaterializer = SystemMaterializer(system)

  Source.repeat(1).throttle(10, 1.second).map { _ =>
    val visitEv: Visit = Visit.generate()
    val metricEv: Metric = Metric.generate(visitEv.id.value)
    val sends: Future[List[RecordMetadata]] = Future.sequence(List(visitsProducer.send(visitEv), metricsProducer.send(metricEv)))
    Await.result(sends, 2.seconds)
  }.runWith(Sink.ignore)
    .onComplete(_ => {
      metricsProducer.close()
      visitsProducer.close()
    })
}
