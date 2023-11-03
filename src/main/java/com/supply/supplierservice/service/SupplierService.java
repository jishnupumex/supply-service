package com.supply.supplierservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierService.class);

    public SupplierService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "ExternalOrder", groupId = "supplier-group")
    public void consumeSupplierRequest(String message) {
    if (message.contains("Order more")) {
        int prodQtyToAdd = 5;
        String stockUpdateMessage = "Stock added " + prodQtyToAdd;
        kafkaTemplate.send("stock-update", stockUpdateMessage);
        }
    }
}


