package eu.nyuu.evprod.models

import java.time.OffsetDateTime
import java.util.UUID

import eu.nyuu.evprod.utils.AutoRecord

case class Visit(
                  id: Id[Visit],
                  timestamp: OffsetDateTime,
                  sourceIp: String,
                  url: String,
                ) extends Event[Visit]

object Visit extends AutoRecord[Visit] {
  override val topic: String = "visits"

  val random = new scala.util.Random()
  val urls = List(
    "/home",
    "/me",
    "/store",
    "/store/books",
    "/store/tech",
    "/store/tech/phones",
    "/store/tech/home-automation",
    "/store/tech/home-automation/heating",
    "/store/tech/home-automation/lamps",
    "/store/tech/home-automation/alarms",
    "/store/tech/home-automation/coffee",
    "/store/tech/home-automation/42",
    "/store/tech/health",
    "/store/tech/computers",
    "/store/tech/tv",
    "/store/photo",
    "/store/photo/reflex",
    "/store/photo/bridge",
    "/store/photo/camera",
    "/store/books",
  )

  private def newIp: String = {
    s"${random.nextInt(224)}.${random.nextInt(256)}.${random.nextInt(256)}.${random.nextInt(254) + 1}"
  }

  def generate(): Visit = {
    Visit(
      Id[Visit](UUID.randomUUID().toString),
      OffsetDateTime.now(),
      newIp,
      urls(random.nextInt(urls.length)),
    )
  }
}