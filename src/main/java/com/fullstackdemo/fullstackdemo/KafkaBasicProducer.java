package com.fullstackdemo.fullstackdemo;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KafkaBasicProducer {
private static final Logger log =org.slf4j.LoggerFactory.getLogger(KafkaBasicProducer.class.getSimpleName());
	public static void main(String[] args) {
		SpringApplication.run(KafkaBasicProducer.class, args);
	
log.info("welcome to the Kafka Basics");
//create producer properties
Properties properties=new Properties();
//properties.setProperty("bootstrap.servers", "127.0.0.1.9092");
properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

//set producer properties 
properties.setProperty("key.serializer", StringSerializer.class.getName());
properties.setProperty("value.serializer",StringSerializer.class.getName());



//create the producer
KafkaProducer<String, String> producer=new KafkaProducer<>(properties);

// create producer record
ProducerRecord<String, String> producerRecord=new ProducerRecord<>("demo_java","first message");
//send data

producer.send(producerRecord);

// flush and close the producer-->tell the producer to send all data and block untill done -- synchronous
producer.flush();

producer.close();
}
}