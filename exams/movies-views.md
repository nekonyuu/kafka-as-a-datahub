# Projet - Kafka Streams - Sujet n°1

Vous êtes arrivé dans la société KazaaMovies, spécialisée dans le streaming de films à destination des particuliers.

Ils viennent de mettre en place après plusieurs années de développement une dimension sociale à leur offre de streaming, laquelle va vous fournir des informations sur les interactions utilisateurs et leurs vues, mais ils n'ont mis en place que la première partie technique: la récupération des données.

**Votre but: rentre utilisable ces données, lesquelles arrivent dans plusieurs topics Kafka.**

## Sources de données

Il y a actuellement deux topics qui vous fournissent des données:

  * `views` : il contient des évènements de visionnement d'un film
    * Titre du film, dans quelle catégorie rentre la vue (début, partielle, fini le film)
    * Structure :
```json
{
    "_id": 1,
    "title": "Kill Bill",
    "view_category": "half"
}
```
  * `likes` : il contient les notes données aux films par les utilisateurs
    * On reçoit un évènement par note donnée
    * Structure
```json
{
    "_id": 1,
    "score": 4.8
}
```

## Environnement

Vous pouvez partir d'un template selon votre langage:
  * Scala: https://git.esgi.nyuu.eu/nekonyuu/kafka-as-a-datahub-project-template-scala
    * Akka HTTP, Kafka Streams
  * Java: https://git.esgi.nyuu.eu/nekonyuu/kafka-as-a-datahub-project-template-java
    * Kafka Streams, REST framework à votre charge

Vous devrez aussi reprendre le fichier `docker-compose.yml` des exercices :

  * Replacez vous dans le dossier `platform/docker` du dépôt d'exercices
  * `docker-compose down`
  * remplacer l'entrée `injector` par les lignes suivantes (l'indentation est importante) :

```yaml
  injector:
    image: nekonyuu/tp-kafka-movies-views:1.0
    depends_on:
      - broker
    hostname: injector
    environment:
      BROKER_HOST: broker:29092
    container_name: injector

```
  * `docker-compose up -d` 

Vous êtes parés !

## Applications à implémenter

Le Product Owner vous demande de lui fournir les informations suivantes

  * Nombre de vues par film, catégorisées par type de vue: 
    * arrêt en début de film (< 10% du film vu)
    * arrêt en milieu de film (< 90% du film vu)
    * film terminé (> 90% du film vu)
  * Top 10 des films ayant les meilleurs retours (score > 4)
  * Top 10 des films ayant les moins bons retours (score < 2)

Tout ceci doit être exposé sur une API REST (format de sortie JSON) ayant le schéma suivant:

  * GET /movies/:id
    * Donne le nombre de vues et leur distribution pour un ID de film donné
  * GET /stats/ten/best
    * Donne les 10 meilleurs films
  * GET /stats/ten/worst
    * Donne les 10 pires films


## Scope

Il n'est pas demandé que l'application implémentée pour ces besoins gère le lancement d'instances multiples (ce qui impliquerait la gestion de stores distribués et l'usage de RPC). 

Toutefois, un effort fait en ce sens pourra compter comme bonus.

## Documentation

  * Kafka Stream DSL documentation: https://docs.confluent.io/current/streams/developer-guide/dsl-api.html
  * Kafka Stream Scala doc: https://kafka.apache.org/20/documentation/streams/developer-guide/dsl-api.html#scala-dsl
  * Kafka Streams Interactive Queries: https://docs.confluent.io/current/streams/developer-guide/interactive-queries.html
  * Akka HTTP (REST API): https://doc.akka.io/docs/akka-http/current/routing-dsl/index.html