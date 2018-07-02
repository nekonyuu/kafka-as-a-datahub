# Utilisation d'un consumer et d'un producer

## Contexte

Des capteurs fournissent des informations d'utilisation CPU, RAM et disque globaux concernant 
des datacenters dans plusieurs régions du monde. Chacun de ces capteurs écrit 
dans un topic kafka nommé `sensors`.

Ces messages possèdent la forme suivante :

```json
{"timestamp": 1530472776, "source": "2d53ead54f0d0cf3431eb4d2bfccb6894a0037c3d9ea76bd8ee55b8bd7e50c0b", "metric": "ram", "value": 4002}
```

## But

### Part 1

Vous devez désanonymiser le nom de la source en appelant le service qui fournit ces données, 
via un appel HTTP REST GET `/source/hash/<hash>` sur le web service donné, puis produire ce même
message désanonymisé sur un topic nommé `sensors_yourname_clear`.

Exemple de réponse de l'API sur GET `/source/name/ams2` :

```json
{"source_name": "ams2", "group": "eur"}
```

Une fois désanonymisé, vous devrez calculer des moyennes de chaque métrique sur 30 secondes, 1 minute et 5 minutes et les produire dans un topic nommé `sensors_yourname_aggs`.

### Part 2

Vous devez maintenant envoyer les messages que vous produisez avec une clé.

L'idée est que vous puissiez garantir l'écriture des messages concernant le 
même groupe dans la même partition.

Vous pouvez connaître le groupe d'une source via les mêmes appels que 
précedemment, champ `group` du JSON.

### Part 3 (bonus)

Nous voudrions maintenant + de garanties sur l'écriture des messages, de manière à n'en perdre aucun.

Pour tous les appels de production de messages, faites en sorte que
le paramètre d'acquittement soit maintenant `acks=all`, s'assurant de 
l'écriture des messages sur tous les brokers.

## Informations

 * Broker Kafka: `10.33.0.42:29092`
 * API: http://10.33.0.42:5000