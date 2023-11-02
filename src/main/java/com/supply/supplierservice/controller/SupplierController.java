package com.supply.supplierservice.controller;

import com.supply.supplierservice.service.SupplierService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restapi")
public class SupplierController {
    SupplierService kafkaListner;

    @GetMapping
    public String getMessage(){
        return ("Supply Service Endpoint");
    }

}