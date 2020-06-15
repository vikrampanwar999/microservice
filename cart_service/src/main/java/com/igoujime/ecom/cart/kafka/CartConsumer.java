package com.igoujime.ecom.cart.kafka;

import com.igoujime.ecom.cart.entity.Cart;
import com.igoujime.ecom.cart.entity.CartState;
import com.igoujime.ecom.cart.mapper.JsonMapperWrapper;
import com.igoujime.ecom.cart.service.CartService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class CartConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartConsumer.class);

//    private CountDownLatch latch = new CountDownLatch(1);

    private final CartService cartService;
    private final KafkaCartService kafkaCartService;
    private final JsonMapperWrapper jsonMapper;

    @Value("${spring.kafka.producer.topic.product}")
    private String productTopic;

    @Autowired
    public CartConsumer(CartService cartService, KafkaCartService kafkaCartService, JsonMapperWrapper jsonMapper) {
        this.cartService = cartService;
        this.kafkaCartService = kafkaCartService;
        this.jsonMapper = jsonMapper;
    }


    @KafkaListener(topics = "${spring.kafka.consumer.topic.cart}")
    @LoadBalanced
    public void receiveProduct(ConsumerRecord<String, String> consumerRecord) {
        LOGGER.info("Received topic={}, key={}, Cart={}", consumerRecord.topic(), consumerRecord.key(), consumerRecord.value());
        if (consumerRecord.key().equals(KeySet.UPDATE_CART.toString())) {
            Cart cart = validateCart(jsonMapper.readCartValue(consumerRecord.value()));
            updateProduct(cart);
        }
//        latch.countDown();
    }

    /**
     * Validate the cart
     *
     * @param cart
     */
    private Cart validateCart(Cart cart) {
        if (cart != null) {
            Cart cartToSave = cartService.findOne(cart.getId());
            cartToSave.setCartState(CartState.VALID);
            return cartService.save(cartToSave);
        }
        return null;
    }

    /**
     * Update the qnt of products
     *
     * @param cart
     */
    private void updateProduct(Cart cart) {
        if (cart != null) {
            kafkaCartService.send(productTopic, KeySet.UPDATE_QNT_PRODUCT, jsonMapper.writeValue(cart.getProducts()));
        }
    }

}
