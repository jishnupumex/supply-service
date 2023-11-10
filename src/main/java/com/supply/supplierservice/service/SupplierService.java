package com.supply.supplierservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supply.supplierservice.data.repositories.InventoryRepo;
import com.supply.supplierservice.data.repositories.UserOrderRepository;
import com.supply.supplierservice.entity.Inventory;
import com.supply.supplierservice.entity.OrderRequest;
import com.supply.supplierservice.entity.OrderStatus;
import com.supply.supplierservice.entity.UserOrders;
import com.supply.supplierservice.exceptions.InsufficientStockException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierService {
    private final KafkaTemplate<String, OrderRequest> kafkaTemplate;
    private final UserOrderRepository userOrderRepository;
    private final InventoryRepo inventoryRepo;
    private final ObjectMapper objectMapper;
    private final OrderRequest orderRequest;
    private final UserOrders userOrders;
    @KafkaListener(topics = "${spring.kafka.topic.name.topic1}", groupId = "${spring.kafka.consumer.group-id.topic1}")
    @Transactional
    public void consumeSupplierRequest(String orderRequestAsString) throws JsonProcessingException {

        try {
            OrderRequest orderRequest = objectMapper.readValue(orderRequestAsString, OrderRequest.class);
            log.info("Seller request received -> {}", orderRequest);
            log.info("Prod ID -> {} , Required Prod Qty {} of OrderID - {}", orderRequest.getProdNumber(), orderRequest.getRequiredQty(), orderRequest.getOrderId());

            // Retrieve current available quantity
            Inventory inventory = inventoryRepo.findByProdId(Math.toIntExact(orderRequest.getProdNumber()));
            int currentQty = inventory.getProdQty();
            int additionalStockQty = orderRequest.getRequiredQty() + 10;

            // Ensure the quantity will not go negative
            int updatedQty = currentQty + additionalStockQty ;
            if (updatedQty < 0) {
                // Handle insufficient stock scenario
                throw new InsufficientStockException("Insufficient stock for product: " + orderRequest.getProdNumber());
            }

            // Update the inventory with the new quantity
            inventoryRepo.updateInventoryByProdNumber(orderRequest.getProdNumber(), updatedQty);


            kafkaTemplate.send("OrderFulfillmentBySupplier", orderRequest);
            log.info("Inventory updated and sent to logistics");
        } catch (Exception e) {
            // Handle exceptions accordingly
            log.error("Error updating inventory", e);
        }}
}


