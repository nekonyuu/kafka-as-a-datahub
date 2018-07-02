# Kafka Streams

## Contexte

Des capteurs fournissent des informations d'utilisation CPU, RAM et disque globaux concernant 
des datacenters dans plusieurs régions du monde. Chacun de ces capteurs écrit 
dans un topic kafka nommé `sensors`.

Ces messages possèdent la forme suivante :

```json
{"timestamp": 1530472776, "source": "2d53ead54f0d0cf3431eb4d2bfccb6894a0037c3d9ea76bd8ee55b8bd7e50c0b", "metric": "ram", "value": 4002}
```

## But

Réimplémenter ce que vous avez fait en `01-consumer-producer.md`, en Kafka Streams :).

Documentation Kafka Streams 
  * https://docs.confluent.io/current/streams/developer-guide/dsl-api.html

Tâches:

  * Désanonymiser le nom de la source
  * Grouper par nom de groupe
  * Calculer des métriques par source et par groupe.    

## Informations

 * Broker Kafka: `10.33.0.42:29092`
 * API: http://10.33.0.42:5000