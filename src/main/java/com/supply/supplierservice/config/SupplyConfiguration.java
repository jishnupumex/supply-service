package com.supply.supplierservice.config;

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
}
