package com.igoujime.ecom.product.kafka;

import com.igoujime.ecom.product.entity.Product;
import com.igoujime.ecom.product.mapper.JsonMapperWrapper;
import com.igoujime.ecom.product.service.ProductService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);

    private final ProductService productService;
    private final JsonMapperWrapper jsonMapperWrapper;

    @Autowired
    public ProductConsumer(ProductService productService, JsonMapperWrapper jsonMapperWrapper) {
        this.productService = productService;
        this.jsonMapperWrapper = jsonMapperWrapper;
    }

//    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "${spring.kafka.consumer.topic.product}")
    @LoadBalanced
    public void receiveProduct(ConsumerRecord<String, String> consumerRecord) {
        LOGGER.info("Received topic={}, key={}, Products={}", consumerRecord.topic(), consumerRecord.key(), consumerRecord.value());
        if (consumerRecord.key().equals(KeySet.UPDATE_QNT_PRODUCT.toString())) {
            updateProductQnt(jsonMapperWrapper.readProductValue(consumerRecord.value()));
        }
//        latch.countDown();
    }

    /**
     * Update the quantity of products
     *
     * @param productList
     */
    private void updateProductQnt(List<Product> productList) {
        if (productList != null) {
            productList.forEach(product -> {
                Product productToSave = productService.findOne(product.getId());
                productToSave.setQuantity(productToSave.getQuantity() - product.getQuantity());
                productService.save(productToSave);
            });
        }
    }

}
