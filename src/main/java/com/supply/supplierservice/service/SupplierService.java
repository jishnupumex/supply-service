package com.supply.supplierservice.service;

import com.scm.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.scm.UserOrder;

@Service
public class SupplierService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierService.class);


//    @KafkaListener(topics = "${spring.kafka.topic.name.topic1}", groupId = "${spring.kafka.consumer.group-id.topic1}")
//    public void consumeUserOrderKafkaTopic(UserOrder userOrder) {
//        LOGGER.info(String.format("User Order status -> %s", userOrder.toString()));
//    }

}


