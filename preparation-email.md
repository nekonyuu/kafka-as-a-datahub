Bonjour,

Vous allez assister la semaine prochaine à mon cours sur Kafka et ses usages orientés stream. 

Pour que les TPs se déroulent sans retards, pensez à installer les outils suivants sur votre machine :

  * IntelliJ IDEA avec le plugin Scala (https://www.jetbrains.com/fr-fr/idea/download, version community)
  * Docker
    * Linux: passez par votre gestionnaire de package ;
    * Windows/Mac OS: allez sur https://hub.docker.com/search?q=&type=edition&offering=community et téléchargez l'installeur idoïne.

Une fois ces outils installés, effectuez les commandes suivantes dans une invite de commande (Powershell ou Bash) en étant sur une connexion internet viable (cela peut prendre une dizaine de minutes):

```bash
docker pull confluentinc/cp-zookeeper:5.4.0
docker pull confluentinc/cp-server:5.4.0
docker pull confluentinc/cp-schema-registry:5.4.0
docker pull confluentinc/cp-ksql-server:5.4.0
docker pull confluentinc/cp-ksql-cli:5.4.0
docker pull cnfldemos/cp-server-connect-datagen:0.2.0-5.4.0
docker pull confluentinc/cp-enterprise-control-center:5.4.0
docker pull confluentinc/cp-kafka-rest:5.4.0
docker pull nekonyuu/tp-kafka-ev-producer:1.0.2
```

Une fois ceci terminé, vous êtes parés pour le cours.

Bonne journée.