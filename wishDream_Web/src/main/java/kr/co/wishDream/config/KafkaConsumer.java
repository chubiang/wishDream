package kr.co.wishDream.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

public class KafkaConsumer {
private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Value("${kafka.server}")
	String kafkaServers;
	
	@Value("${kafka.topics}") 
	String[] topics;
	
	@Bean
	public Map<String, Object> kafkaConsumerConfiguration() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
		config.put(ConsumerConfig.CLIENT_ID_CONFIG, "wishdream-consumer");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "wishdream-group_0");
		config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return config;
	}
	
	@Bean
	public ReceiverOptions<String, String> receiverOptions(@Value("${kafka.topics}") String[] topics) {
		ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(kafkaConsumerConfiguration());
		LOG.info("## receiverOptions.groupId = "+receiverOptions.groupId());
		return receiverOptions.subscription(Arrays.asList(topics))
				.withKeyDeserializer(new StringDeserializer())
				.withValueDeserializer(new StringDeserializer())
				.addAssignListener(consumer -> LOG.info("## Group Assigned Successfully - {}"
						, Arrays.asList(consumer.stream().findFirst().get().topicPartition().topic(), 
								consumer.stream().findFirst().get().topicPartition().partition())))
				.addRevokeListener(consumer -> LOG.info("## Group Revoked Successfully - {}"
						, Arrays.asList(consumer.stream().findFirst().get().topicPartition().topic(),
								consumer.stream().findFirst().get().topicPartition().partition())));
	}
	
	// consumer receiver
	@Bean(value = "reactiveKafkaReceiver")
	public Flux<ReceiverRecord<String, String>> reactiveKafkaReceiver() {
		LOG.info("## reactiveKafkaReceiver = "+this.topics);
		return KafkaReceiver.create(receiverOptions(this.topics)).receive();
	}
	
}
