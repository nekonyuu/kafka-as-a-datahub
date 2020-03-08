import Dependencies._

enablePlugins(JavaServerAppPackaging)
enablePlugins(DockerPlugin)

resolvers += Resolver.bintrayRepo("ovotech", "maven")

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "1.0.2"
ThisBuild / organization     := "eu.nyuu"
ThisBuild / organizationName := "nyuulabs"

lazy val root = (project in file("."))
  .settings(
    name := "web-events-producer",
    libraryDependencies ++= List(
      scalaTest % Test,
      kafka,
      kafkaSerdeCore,
      akka,
      circe,
      circeGeneric,
      circeParser,
      kafkaSerdeCirce,
      logback,
      scalaLogging
    )
  )

packageName in Docker := "nekonyuu/tp-kafka-ev-producer"

version in Docker := version.value
