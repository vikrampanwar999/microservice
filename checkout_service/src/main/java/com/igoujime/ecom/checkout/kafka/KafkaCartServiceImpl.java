package com.igoujime.ecom.checkout.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaCartServiceImpl implements KafkaCartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCartServiceImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaCartServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, KeySet key, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}', key='{}'", payload, topic, key);
        kafkaTemplate.send(topic, key.toString(), payload);
    }

}
