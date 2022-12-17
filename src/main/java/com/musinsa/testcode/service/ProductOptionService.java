package com.musinsa.testcode.service;

import com.musinsa.testcode.entity.ProductOption;
import com.musinsa.testcode.repo.ProductOptionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductOptionService {

    private ProductOptionRepository productOptionRepository;

    public JSONObject getProductOptionInfo(String productName) {
        List<ProductOption> productOptionOptionList = productOptionRepository.findAllByProductName(productName);
        log.info("[{}.{}] param : {}, resObj : {}", this.getClass().getName(), "getProductOptionInfo", productName, productOptionOptionList);
        JSONObject resObj = new JSONObject();

        resObj.put("returnCode" , productOptionOptionList.isEmpty() ? "9999": "1");
        resObj.put("returnData" , productOptionOptionList.isEmpty() ? null : productOptionOptionList);
        return resObj;
    }

    public JSONObject getProductOptionInfo(String productName, String optionName) {
        JSONObject resObj = new JSONObject();

        Optional<ProductOption> productOption = productOptionRepository.findByProductNameAndOptionName(productName, optionName);
        log.info("[{}.{}] productName : {}, optionName : {}, resObj : {}", this.getClass().getName(), "getProductOptionInfo", productName, optionName, productOption);
        resObj.put("returnCode", productOption.isPresent() ? "1" : "9999");
        resObj.put("returnData", productOption.isPresent() ? productOption.get() : null);

        return resObj;
    }

}
