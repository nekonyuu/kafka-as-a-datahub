# Web Events simulator

This app simply produces web visits events into Kafka in order
to apply some processing to them.

## Cleaning topic

```
kafka-topics.sh --zookeeper localhost:2181 --delete --topic hits
```