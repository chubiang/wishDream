package kr.co.wishDream.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
		config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
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
	public KafkaReceiver<String, String> reactiveKafkaReceiver() {
		LOG.info("## reactiveKafkaReceiver = "+this.topics);
		return KafkaReceiver.create(receiverOptions(this.topics));
	}
	
	@Bean
	public Map<String, Object> kafkaProducerConfiguration() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
		config.put(ProducerConfig.CLIENT_ID_CONFIG, "wishdream-producer");
		config.put(ProducerConfig.ACKS_CONFIG, "all");
		config.put(ProducerConfig.RETRIES_CONFIG, "0");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return config;
	}
	
	@Bean
	public SenderOptions<String, String> senderOptions() {
		SenderOptions<String, String> senderOptions = SenderOptions.create(kafkaProducerConfiguration());
		LOG.info("## senderOptions = "+senderOptions.producerProperties());
		return senderOptions;
	}
	
	// producer sender
	@Bean(value = "reactiveKafkaSender")
	public KafkaSender<String, String> reactiveKafkaSender() {
		LOG.info("## reactiveKafkaSender = "+senderOptions());
		return KafkaSender.create(senderOptions());
	}
	
	// TODO: KafkaSchedulers
	
	
}
