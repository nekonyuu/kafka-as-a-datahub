package eu.nyuu.evprod.utils

import java.util.Properties

import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.Serializer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Producer {
  val logger: Logger = Logger("Producer")

  def apply[V] = new ProducerBuilder[V]

  class ProducerBuilder[V] {
    def apply[K](config: Properties)(implicit record: Record[K, V],
                                     keySerializer: Serializer[K],
                                     valueSerializer: Serializer[V]): KafkaProducer[K, V] =
      new KafkaProducer(config, keySerializer, valueSerializer)
  }

  implicit class KafkaProducerOps[K, V](kafkaProducer: KafkaProducer[K, V]) {
    def send(value: V)(implicit record: Record[K, V]): Future[RecordMetadata] = Future {
      logger.info(s"Sending record $value")
      kafkaProducer.send(new ProducerRecord(record.destinationTopic, null, record.timestamp(value), record.key(value), value)).get()
    }
  }

}


