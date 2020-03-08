package eu.nyuu.evprod

import java.time.OffsetDateTime

import eu.nyuu.evprod.models.{Id, Metric, Visit}
import io.circe.Encoder

import scala.concurrent.duration.Duration

package object encoders {
  implicit val idVisitEncoder: Encoder[Id[Visit]] = Encoder.encodeString.contramap[Id[Visit]](_.value)
  implicit val idMetricEncoder: Encoder[Id[Metric]] = Encoder.encodeString.contramap[Id[Metric]](_.value)

  implicit val offsetDateTimeEncoder: Encoder[OffsetDateTime] = Encoder.encodeString.contramap[OffsetDateTime](_.toString)
  implicit val durationEncoder: Encoder[Duration] = Encoder.encodeLong.contramap[Duration](_.toMillis)
}
