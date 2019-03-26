package com.xiaoming.seller.controller;

import com.xiaoming.entity.Product;
import com.xiaoming.seller.service.ProductRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品相关
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRpcService productRpcService;

    @RequestMapping("/{id}")
    public Product findOne(@PathVariable String id){
        return productRpcService.findOne(id);
    }
}
