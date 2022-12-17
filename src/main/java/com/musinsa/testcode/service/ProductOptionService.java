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
import java.util.concurrent.atomic.AtomicInteger;

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

    public JSONObject changeProductOptionCnt(String productName, String optionName, int updateCnt, String type) {

        JSONObject resObj = new JSONObject();
        String returnCode   = "1";
        String returnMsg    = "success";

        // to-do
        boolean isValidReqParams = true;

        if (isValidReqParams) {
            Optional<ProductOption> productOption = productOptionRepository.findByProductNameAndOptionName(productName, optionName);
            if (productOption.isPresent()) {
                ProductOption productOptionObj = productOption.get();
                int productOptionCnt = productOptionObj.getProductOptionCnt();
                int updateProductOptionCnt = productOptionCnt;
                if (type.equals("increase")) {
                    updateProductOptionCnt += updateCnt;
                } else if (type.equals("decrease")) {
                    updateProductOptionCnt -= updateCnt;
                }

                if (updateProductOptionCnt < 0) {
                    returnCode  = "2000";
                    returnMsg   = "Can not update as minus value";
                } else {
                    AtomicInteger atomicProductOptionCnt = new AtomicInteger(productOptionCnt);
                    boolean isSameProductOptionCnt = atomicProductOptionCnt.compareAndSet(productOptionCnt, updateProductOptionCnt);
                    if (isSameProductOptionCnt) {
                        productOptionObj.setProductOptionCnt(updateProductOptionCnt);
                        ProductOption updatedProductOption = productOptionRepository.save(productOptionObj);
                        resObj.put("returnData", updatedProductOption);
                    } else {
                        returnCode  = "3000";
                        returnMsg   = "data sync issue";
                    }
                }
            } else {
                returnCode  = "9999";
                returnMsg   = "no data";
            }
        } else {
            returnCode  = "1000";
            returnMsg   = "invalid param";
        }
        resObj.put("returnCode" , returnCode);
        resObj.put("returnMsg"  , returnMsg);
        return resObj;
    }

}
