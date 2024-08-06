package com.fullstackdemo.fullstackdemo;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KafkaBasicProducerWithCallback {
private static final Logger log =org.slf4j.LoggerFactory.getLogger(KafkaBasicProducerWithCallback.class.getSimpleName());
	public static void main(String[] args) {
		SpringApplication.run(KafkaBasicProducerWithCallback.class, args);
	
log.info("welcome to the Kafka Basics");
//create producer properties
Properties properties=new Properties();
properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

//set producer properties 
properties.setProperty("key.serializer", StringSerializer.class.getName());
properties.setProperty("value.serializer",StringSerializer.class.getName());



//create the producer
KafkaProducer<String, String> producer=new KafkaProducer<>(properties);

// create producer record

//topic cretion command - kafka-topics.bat --create --topic learningkafka --bootstrap-server localhost:9092
ProducerRecord<String, String> producerRecord=new ProducerRecord<>("first_topic","first message");
//send data

producer.send(producerRecord,new Callback() {
	
	@Override
	public void onCompletion(RecordMetadata metadata, Exception e) {
		// will execute every time a record successfully sent or an exception is thrown
		
		if(e==null) {
			log.info("Recieved new metadata\n" +
		                "Topic:" + metadata.topic() + "\n" +
		                "Partition:" + metadata.partition() + "\n" +
		                "Offset:" + metadata.offset() + "\n" +
		                "Timestamp:" + metadata.timestamp() + "\n");						
					
		}
		else {
			log.error("error while producing",e);
		}
		
	}
});


// flush and close the producer-->tell the producer to send all data and block untill done -- synchronous
producer.flush();

producer.close();
}
}