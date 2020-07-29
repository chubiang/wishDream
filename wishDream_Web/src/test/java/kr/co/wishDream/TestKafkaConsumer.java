package kr.co.wishDream;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

public class TestKafkaConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(TestKafkaConsumer.class);
	
	private static final String KAFKA_SERVERS = "localhost:9099";
	private static final String TOPIC = "alarm-topic";
	
	private final ReceiverOptions<Integer, String> receiverOptions;
    private final SimpleDateFormat dateFormat;
    
    public TestKafkaConsumer(String kafkaServers) {
    	Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, "wishdream-consumer");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "wishdream-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		receiverOptions = ReceiverOptions.create(props);
		dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
    }
    
    public void consumeMessage(String topic, CountDownLatch latch) {
    	ReceiverOptions<Integer, String> options = receiverOptions.subscription(Collections.singleton(topic))
    				.addAssignListener(partitions -> LOG.debug("onPartitionAssigned {}", partitions))
    				.addRevokeListener(partitions -> LOG.debug("onPartitionRevoked {}", partitions));
    	 Flux<ReceiverRecord<Integer, String>> kafkaFlux = KafkaReceiver.create(options).receive();
    	 kafkaFlux.subscribe(record -> {
             ReceiverOffset offset = record.receiverOffset();
             System.out.printf("Received message: topic-partition=%s offset=%d timestamp=%s key=%d value=%s\n",
                     offset.topicPartition(),
                     offset.offset(),
                     dateFormat.format(new Date(record.timestamp())),
                     record.key(),
                     record.value());
             offset.acknowledge();
             latch.countDown();
         }).dispose();;
    }
    

    public static void main(String[] args) throws Exception {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        TestKafkaConsumer consumer = new TestKafkaConsumer(KAFKA_SERVERS);
        consumer.consumeMessage(TOPIC, latch);
        latch.await(10, TimeUnit.SECONDS);
        
    }
}
