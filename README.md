# Kafka as a Data Hub

[![Join the chat at https://gitter.im/kafka-as-a-datahub-exercices/community](https://badges.gitter.im/kafka-as-a-datahub-exercices/community.svg)](https://gitter.im/kafka-as-a-datahub-exercices/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Plan du cours

  * Pipelines de données et Messaging
    * Impact du Big Data sur les flux de données
    * Le messaging comme dorsale de données
    * Pourquoi Kafka est venu au monde ?
  * Concepts et Architecture de Kafka
    * Kafka-centric architecture
    * "Queue as a Journal"
    * Différences de Kafka avec les systèmes traditionnels
    * Zookeeper, pourquoi ?
  * Consumers/Producers
    * Fonctionnement d'un producer
    * Fonctionnement d'un consumer
      * fetch request, heartbeats, session, répartition de charge cliente
  * Réplication, durabilité et impact sur les performances
    * Performance - Disponibilité - Durabilité
        * Acquittement de messages
        * Paramètres autour de la tolérance à la panne
    * Fonctionnement de la réplication sur Kafka (ISR, replicas, leaders/followers)
  * Rétention de données
    * Compression (compact)
    * Nettoyage (clean)
  * Kafka Streams
    * Pourquoi un nouvel arrivant ?
    * Notions basiques
    * Deep Dive sur le Domain Specific Language fourni
      * Aggrégations, filtres, transformations, ...

## Exercices

  * [Utilisation des clients Kafka officiels (Consumer/Producer)](exercices/01-consumer-producer.md)
  * [Gestion des offsets](exercices/02-offsets.md)
  * [Kafka Stream](exercices/03-kafka-streams.md)
