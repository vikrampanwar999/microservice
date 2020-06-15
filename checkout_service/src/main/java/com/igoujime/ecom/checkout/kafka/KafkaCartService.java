package com.igoujime.ecom.checkout.kafka;

public interface KafkaCartService {

    void send(String topic, KeySet key, String payload);

}
