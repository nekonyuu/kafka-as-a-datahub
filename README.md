# Kafka as a Data Hub

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

## Exercices

  * Utilisation des clients Kafka officiels (Consumer/Producer)
  * Gestion des offsets
  * Kafka Stream

## Exam

Voir [là](exam/goal.md)