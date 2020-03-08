package eu.nyuu.evprod.utils

trait Record[K, V] {
  def destinationTopic: String

  def key(value: V): K

  def timestamp(value: V): Long
}