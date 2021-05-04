import WebSocket from "ws";
import Kafka from "node-rdkafka";

const wssUrlBTC = "wss://stream.binance.com:9443/ws/btcusdt@trade";
const ws = new WebSocket(wssUrlBTC);

var producer = new Kafka.Producer({
  "metadata.broker.list": "pkc-ewzgj.europe-west4.gcp.confluent.cloud:9092",
  "security.protocol": "sasl_ssl",
  "sasl.mechanisms": "PLAIN",
  "sasl.username": "AP7HIDDZPNRBS4GX",
  "sasl.password":
    "4vOcl3vTTtU+6gUjNq4IGxtJNq3A/uW6F1PQe3z6BaX+DIHDgD6xKq9rSa2McomY",
});

interface BinanceTrade {
  e: string;
  E: number;
  s: string;
  t: number;
  p: string;
  q: string;
  b: number;
  a: number;
  T: number;
  m: boolean;
  M: boolean;
}

producer.connect();

producer.on("ready", function () {
  ws.on("message", function incoming(data: string) {
    let trade: BinanceTrade = JSON.parse(data);
    producer.produce(
      "trades",
      null,
      Buffer.from(JSON.stringify(trade)),
      trade.s,
      Date.now()
    );
  });
});
