package eu.nyuu.evprod.models

import java.time.OffsetDateTime

trait Event[T] {
  def id: Id[T]
  def timestamp: OffsetDateTime
}