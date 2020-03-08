import sbt._

object Dependencies {
  val circeVersion = "0.11.1"
  val kafkaSerializationV = "0.4.1"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val kafka = "org.apache.kafka" % "kafka-clients" % "2.4.0"
  lazy val kafkaSerdeCore = "com.ovoenergy" %% "kafka-serialization-core" % kafkaSerializationV
  lazy val kafkaSerdeCirce = "com.ovoenergy" %% "kafka-serialization-circe" % kafkaSerializationV
  lazy val circe = "io.circe" %% "circe-core" % circeVersion
  lazy val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
  lazy val circeParser = "io.circe" %% "circe-parser" % circeVersion
  lazy val akka = "com.typesafe.akka" %% "akka-stream" % "2.6.3"

  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
}
