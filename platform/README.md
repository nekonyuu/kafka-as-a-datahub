# Running Kafka

Vous pouvez lancer un broker Kafka sur votre machine avec Doc ker.

D'abord, installez Docker (for Windows, Mac, ou le package linux), puis docker-compose (https://docs.docker.com/compose/install/).

Vous pouvez ensuite aller dans le dossier `docker`, editez `docker-compose.yml` en remplaçant YOUR_IP par votre adresse IP, puis faites :

```bash
docker-compose up -d
```

Vous pouvez arrêter le broker en faisant :

```bash
docker-compose stop
```

et le redémarrer en faisant:

```bash
docker-compose start -d
```