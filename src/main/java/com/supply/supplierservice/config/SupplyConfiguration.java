package com.supply.supplierservice.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class SupplyConfiguration {

    @Value("${spring.kafka.topic.name.topic1}")
    private String topicInventory;

    @Bean
    public NewTopic UserOrderTopic(){
        return TopicBuilder.name(topicInventory)
                .build();
    }

    @Value("${spring.kafka.topic.name.topic2}")
    private String topicStockUpdate;

    @Bean
    public NewTopic OrderUpdateTopic(){
        return TopicBuilder.name(topicStockUpdate)
                .build();
    }

    @Value("${spring.kafka.topic.name.OrderFulfillment}")
    private String topicOrderFulfillmentName;

    @Bean
    public NewTopic OrderFulfillmentTopic() {
        return TopicBuilder.name(topicOrderFulfillmentName)
                .build();
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Ignore unknown properties during deserialization
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
