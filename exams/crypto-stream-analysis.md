# Projet 2 - Statistiques de trades sur la plateforme Binance.

Vous vous intéressez de près aux mouvements de cryptomonnaie, et voulez connaître l'évolution de celles-ci via
les trades qui y sont faits.

**Votre but: sortir quelques statistiques à partir des données issues de l'API Stream de Binance.**

## Sources de données

Il y a actuellement un topic qui vous fournit des données:

- `trades` : il contient chaque évènement de trade avec les informations qui lui sont liées
  - Le type d'évènement (trade tout le temps, ici)
  - Le timestamp du trade
  - La paire concernée (symbol)
  - Le prix d'achat/vente
  - La quantité achetée/vendue
  - Et d'autres informations...

```json
{
  "e": "trade", // Event type
  "E": 123456789, // Event time
  "s": "BNBBTC", // Symbol
  "t": 12345, // Trade ID
  "p": "0.001", // Price
  "q": "100", // Quantity
  "b": 88, // Buyer order ID
  "a": 50, // Seller order ID
  "T": 123456785, // Trade time
  "m": true, // Is the buyer the market maker?
  "M": true // Ignore
}
```

## Environnement

Vous allez utiliser un cluster Kafka préalablement déployé.

Les informations le concernant vous seront communiqués par Teams, elles seront à saisir dans le fichier `kafka.properties`.

Concernant le squelette de projet, vous pouvez vous inspirer du skeleton du TP de Kafka Streams: https://github.com/nekonyuu/kafka-as-a-datahub-exercises-skeletons/tree/main/kafka-stream .

## Applications à implémenter

Pour en tirer quelques informations intéressantes, vous allez devoir implémenter les opérations suivantes:

- Nombre de trades par paire
  - sur les 30 dernières secondes
  - sur la dernière minutes
  - sur les 15 dernières minutes
- Prix moyen sur la dernière minute de la paire
- Prix d'ouverture, fermeture, le plus bas et le plus haut d'échange par paire (équivalent des bougies de bourse)
  - Prix = champ price dans le trade
  - A calculer pour chaque minute, et conserver les 15 dernières minutes
- Volume échangé par paire (quantité de la cryptomonnaie)
  - sur les 30 dernières secondes
  - sur la dernière minute
  - sur les 15 dernières minutes

Tout ceci doit être exposé sur une API REST (format de sortie JSON) ayant le schéma suivant:

- GET /trades/:pair/stats
  - Donne des statistiques de trade pour la paire donnée (nombre de trades, volume)

```json
{
  "pair": "ETHUSDT",
  "trades": 200,
  "volume": 280,
  "average_price": 1800
}
```


- GET /trades/:pair/candles
  - Donne le prix d'ouverture, fermeture, le plus bas et le plus haut d'échange pour la paire donnée, et pour les 15 dernières minutes

```json
{
  "pair": "BTCUSDT",
  "candles": [
    {
      "date": "2021-05-03T12:00:00Z",
      "open": 1800,
      "close": 1900,
      "lowest": 1780,
      "highest": 1905
    },
    {
      "date": "2021-05-03T12:01:00Z",
      "open": 1900,
      "close": 1710,
      "lowest": 1600,
      "highest": 2001
    },
    ...
  ]
}
```

## Scope

Il n'est pas demandé que l'application implémentée pour ces besoins gère le lancement d'instances multiples (ce qui impliquerait la gestion de stores distribués et l'usage de RPC).

Toutefois, un effort fait en ce sens pourra compter comme bonus.

## Documentation

- Kafka Stream DSL documentation: https://docs.confluent.io/current/streams/developer-guide/dsl-api.html
- Kafka Stream Scala doc: https://kafka.apache.org/20/documentation/streams/developer-guide/dsl-api.html#scala-dsl
- Kafka Stream Configuration Documentation: https://docs.confluent.io/current/streams/developer-guide/config-streams.html
- Kafka Streams Interactive Queries: https://docs.confluent.io/current/streams/developer-guide/interactive-queries.html
- Akka HTTP (REST API): https://doc.akka.io/docs/akka-http/current/routing-dsl/index.html
- Play JSON: https://www.playframework.com/documentation/2.8.x/ScalaJson
