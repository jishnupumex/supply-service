package com.supply.supplierservice.controller;

import com.supply.supplierservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restapi/supplier")
public class SupplierController {

    private final SupplierService supplierService;

//    @PostMapping
//    public void fulfil(@RequestBody List<UserOrders> orders) {
////         supplierService.fulfil(orders);
//    }

}