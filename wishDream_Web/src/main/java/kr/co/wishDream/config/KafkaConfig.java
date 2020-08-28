package kr.co.wishDream.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaConfig {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaConfig.class);
	
	@Value("${kafka.server}")
	String kafkaServers;
	
	@Value("${kafka.topics}") 
	String[] topics;
	
	@Bean
	public Map<String, Object> kafkaConsumerConfiguration() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
		config.put(ConsumerConfig.CLIENT_ID_CONFIG, "wishdream-consumer");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "wishdream-group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return config;
	}
	
	@Bean
	public ReceiverOptions<Integer, String> receiverOptions(@Value("${kafka.topics}") String[] topics) {
		ReceiverOptions<Integer, String> receiverOptions = ReceiverOptions.create(kafkaConsumerConfiguration());
		LOG.info(receiverOptions.groupId());
		return receiverOptions.subscription(Arrays.asList(topics))
				.addAssignListener(partitions -> LOG.debug("onPartitionsAssigned {}", partitions))
                .addRevokeListener(partitions -> LOG.debug("onPartitionsRevoked {}", partitions));
	}
	
	// consumer receiver
	@Bean
	public Flux<ReceiverRecord<Integer, String>> reactiveKafkaReceiver() {
		return KafkaReceiver.create(receiverOptions(this.topics)).receive();
	}
	
	@Bean
	public Map<String, Object> kafkaProducerConfiguration() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
		config.put(ProducerConfig.CLIENT_ID_CONFIG, "wishdream-producer");
		config.put(ProducerConfig.ACKS_CONFIG, "all");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return config;
	}
	
	@Bean
	public SenderOptions<Integer, String> senderOptions() {
		return SenderOptions.create(kafkaProducerConfiguration());
	}
	
	// producer sender
	@Bean
	public KafkaSender<Integer, String> kafkaSender() {
		return KafkaSender.create(senderOptions());
	}
	
	// TODO: KafkaSchedulers
	
	
}
