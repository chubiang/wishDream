package kr.co.wishDream;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

public class TestKafkaProducer {

	private static final Logger LOG = LoggerFactory.getLogger(TestKafkaProducer.class);
	
	private static final String KAFKA_SERVERS = "localhost:9099";
	private static final String TOPIC = "alarm-topic";

	private final KafkaSender<Integer, String> sender;
	private final SimpleDateFormat dateFormat;
	
	public TestKafkaProducer(String kafkaServers) {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "wishdream-producer");
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		SenderOptions<Integer, String> senderOptions = SenderOptions.create(props);
		
		sender = KafkaSender.create(senderOptions);
		dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
	}
	
	@SuppressWarnings("unchecked")
	public void sendMessages(String topic, int count, CountDownLatch latch) {
		sender.send((Publisher<SenderRecord<Integer, String, Integer>>) Flux.range(1, count)
					.map(i -> SenderRecord.create(new ProducerRecord<Integer, String>(topic, i, "Message_"+i), i))
					.doOnError(e -> LOG.error("ERROR KAFKA SEND MESSAGE---!!!"))
					.subscribe(r -> {
						System.out.printf("Message %d sent successfully, topic-partition=%s-%d \n",
								r.correlationMetadata(),
								r.topic(),
								r.partition());
						latch.countDown();
					}));
	}

	public void close() {
		sender.close();
	}
	
	public static void main(String[] args) throws Exception {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        TestKafkaProducer producer = new TestKafkaProducer(KAFKA_SERVERS);
        producer.sendMessages(TOPIC, count, latch);
        latch.await(10, TimeUnit.SECONDS);
        producer.close();
    }
}
