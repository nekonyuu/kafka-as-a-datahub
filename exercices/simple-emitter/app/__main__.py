import eventgen
import hashlib
import json
from flask import Flask, abort

KAFKA_TOPIC = "sensors"
KAFKA_CUMULATIVE_TOPIC = "business_metrics"

GROUPS = {
    "eur": ["ams1", "ams2", "ams3", "par1", "par2"],
    "america": ["nyc1", "nyc2", "nyc3"],
    "asia": ["tko1", "tko2", "sgn1"]
}

SOURCES = [subl for _, l in GROUPS.items() for subl in l]
SOURCE_2_GROUP = dict()

for grp, src_list in GROUPS.items():
    for src in src_list:
        SOURCE_2_GROUP.update({src: grp})

HASH_2_SOURCE = {hashlib.sha256(s.encode('utf-8')).hexdigest(): s for s in SOURCES}
SOURCE_2_HASH = {s: hashlib.sha256(s.encode('utf-8')).hexdigest() for s in SOURCES}

METRICS = {
    "cpu": range(1, 101, 1),
    "ram": range(1, 4097),
    "disk_free": range(50, 76, 2)
}

CUMULATED_METRICS = {
    "visits": range(1, 11),
    "clicks": range(1, 51),
    "finalized_orders": range(1, 6)
}

# app
app = Flask(__name__)

@app.route("/source/name/<name>")
def get_source_by_name(name):
    try:
        response = {
            "source_name": name,
            "group": SOURCE_2_GROUP[name]
        }

        return json.dumps(response)
    except:
        abort(404)

@app.route("/source/hash/<hash>")
def get_source_by_hash(hash):
    try:
        source_name = HASH_2_SOURCE[hash]
        return get_source_by_name(source_name)
    except:
        abort(404)

events = eventgen.EventGenerator(SOURCES, METRICS, "localhost:29092", KAFKA_TOPIC, interval=0.05)
events_cumulative = eventgen.CumulativeEventGenerator(SOURCES, CUMULATED_METRICS, "localhost:29092", KAFKA_CUMULATIVE_TOPIC, interval=5)

events.start()
events_cumulative.start()

app.run(host="0.0.0.0")