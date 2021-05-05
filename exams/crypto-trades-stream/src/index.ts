import WebSocket from "ws";
import Kafka from "node-rdkafka";

const pair = process.env.PAIR || "btcusdt"
const wssUrlBTC = `wss://stream.binance.com:9443/ws/${pair}@trade`;
const ws = new WebSocket(wssUrlBTC);

var producer = new Kafka.Producer({
  "metadata.broker.list": "pkc-4ygn6.europe-west3.gcp.confluent.cloud:9092",
  "security.protocol": "sasl_ssl",
  "sasl.mechanisms": "PLAIN",
  "sasl.username": "BWJQHNAOSZKVJSSC",
  "sasl.password":
    "W5Rb/LccdOc8QcvgK0Wg2Rv7ezUqhEr+a4G5irTt4LD+U3cggHZmTi4UgxarzpN6",
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
