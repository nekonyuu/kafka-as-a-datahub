package eu.nyuu.evprod.models

import java.time.OffsetDateTime
import java.util.concurrent.TimeUnit

import eu.nyuu.evprod.utils.AutoRecord

import scala.concurrent.duration.Duration

case class Metric(
                   id: Id[Metric],
                   timestamp: OffsetDateTime,
                   latency: Duration,
                 ) extends Event[Metric]


object Metric extends AutoRecord[Metric] {
  override val topic: String = "metrics"
  val random = new scala.util.Random()

  def generate(id: String): Metric = {
    Metric(
      Id[Metric](id),
      OffsetDateTime.now(),
      Duration(random.nextInt(600) + 1, TimeUnit.MILLISECONDS)
    )
  }
}