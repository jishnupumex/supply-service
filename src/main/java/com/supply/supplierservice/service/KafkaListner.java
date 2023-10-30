package com.supply.supplierservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.scm.UserOrder;

@Service
public class KafkaListner {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListner.class);


    @KafkaListener(topics = "UserOrder", groupId = "OrderConsumer")
    public void consume(UserOrder order) {
        LOGGER.info(String.format("Order received -> %s", order.toString()));
    }


//    private Inventory[] inventoryList;
//
//    @KafkaListener(topics = "Communicate", groupId = "Communication-group1")
//    public void listenToKafkaTopic(String messageReceived) {
//        System.out.println("Message received from Kafka is " + messageReceived);
//    }

    //Inventory
//    private String processComparison(Inventory[] inventoryList, UserOrder userOrder) {
//        String status;
//        for (Inventory inventory : inventoryList) {
//            if (inventory.getProdId() == userOrder.getProdId() && inventory.getProdQty() >= userOrder.getProdQty()) {
//                // If the product is found in inventory and quantity is sufficient, respond "Order Confirmed"
//                status = String.valueOf(kafkaTemplate.send("orderStatus", "Order Confirmed"));
//                return status;
//            }
//        }
//        // If no match is found or quantity is insufficient, respond "Order Under Process"
//        status = String.valueOf(kafkaTemplate.send("orderStatus", "Contacting Manufacturer"));
//        return status;
//    }
//
//    @KafkaListener(topics = "inventory", groupId = "Communication-group3")
//    public void listeningToInventory(String inventoryMessage) {
//        System.out.println("From Inventory: " + inventoryMessage);
//        try {
//            // Deserialize the JSON message into an Inventory array
//            inventoryList = objectMapper.readValue(inventoryMessage, Inventory[].class);
//        } catch (JsonProcessingException e) {
//            // Handle deserialization error
//            logger.error("Error deserializing inventory message: {}", e.getMessage(), e);
//            // Handle deserialization error
//            e.printStackTrace();
//        }
//    }
}


