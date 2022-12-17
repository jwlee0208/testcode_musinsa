package com.musinsa.testcode.controller;

import com.musinsa.testcode.service.ProductOptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class ProductOptionController {

    private ProductOptionService productOptionService;

    @RequestMapping(value = "/get/{productName}/cnt")
    public JSONObject getProductOptionInfo(@PathVariable String productName) {
        return productOptionService.getProductOptionInfo(productName);
    }

    @RequestMapping(value = "/get/{productName}/{optionName}/cnt")
    public JSONObject getProductOptionInfo(@PathVariable String productName, @PathVariable String optionName) {
        return productOptionService.getProductOptionInfo(productName, optionName);
    }

}
