package eu.nyuu.evprod.utils

import eu.nyuu.evprod.models.{Event, Id}

trait AutoRecord[T <: Event[T]] {
  val topic = "default"
  implicit val record: Record[Id[T], T] = new Record[Id[T], T] {
    def destinationTopic: String = topic

    def key(ev: T): Id[T] = ev.id

    def timestamp(ev: T): Long = ev.timestamp.toInstant.toEpochMilli
  }
}
