package com.supply.supplierservice.controller;

import com.supply.supplierservice.service.KafkaListner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restapi")
public class KafkaController {
    KafkaListner kafkaListner;

    @GetMapping
    public String getMessage(){
        return ("Kafka Endpoint");
    }

}