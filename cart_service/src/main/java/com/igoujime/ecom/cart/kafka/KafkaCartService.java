package com.igoujime.ecom.cart.kafka;

import com.igoujime.ecom.cart.kafka.KeySet;

public interface KafkaCartService {

    void send(String topic, KeySet key, String payload);

}
