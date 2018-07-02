from collections import namedtuple
from threading import Thread
from random import randint
from hashlib import sha256
from time import sleep, time
from kafka import KafkaProducer
import json

Event = namedtuple("Event", ["timestamp", "source", "metric", "value"])
CumulativeEvent = namedtuple("CumulativeEvent", ["timestamp", "source", "metric", "value", "interval"])

class EventGenerator(Thread):
    def __init__(self, source_list, metrics, broker_address, topic, interval=1):
        super().__init__()
        
        self._source_list = source_list
        self._metrics = metrics
        self._producer = KafkaProducer(bootstrap_servers=broker_address, key_serializer=str.encode, value_serializer=lambda v: json.dumps(v).encode('utf-8'))
        self._topic = topic
        self._interval = interval
    
    def run(self):
        while True:
            source = self._source_list[randint(0, len(self._source_list) - 1)]

            for metric_name, values in self._metrics.items():
                source_hash = sha256(source.encode('utf-8')).hexdigest()
                event = Event(source=source_hash, timestamp=int(time()), metric=metric_name, value=values[randint(0, len(values) - 1)])
                ev = dict(event._asdict())
                self._producer.send(self._topic, key=source_hash, value=ev)
            
            sleep(self._interval)
            
class CumulativeEventGenerator(Thread):
    def __init__(self, source_list, metrics, broker_address, topic, interval=5):
        super().__init__()
        
        self._source_list = source_list
        self._metrics = metrics
        self._producer = KafkaProducer(bootstrap_servers=broker_address, key_serializer=str.encode, value_serializer=lambda v: json.dumps(v).encode('utf-8'))
        self._topic = topic
        self._interval = interval
    
    def run(self):
        while True:
            source = self._source_list[randint(0, len(self._source_list) - 1)]

            for metric_name, values in self._metrics.items():
                source_hash = sha256(source.encode('utf-8')).hexdigest()
                event = CumulativeEvent(
                    source=source_hash, timestamp=int(time()), metric=metric_name, 
                    value=values[randint(0, len(values) - 1)], interval=self._interval
                )
                ev = dict(event._asdict())
                self._producer.send(self._topic, key=source_hash, value=ev)
            
            sleep(self._interval)
