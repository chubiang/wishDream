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
public class KafkaProducer {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Value("${kafka.server}")
	String kafkaServers;
	
	@Value("${kafka.topics}") 
	String[] topics;
	
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
