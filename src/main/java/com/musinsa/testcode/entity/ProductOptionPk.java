package com.musinsa.testcode.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductOptionPk implements Serializable {
    private String productName;
    private String optionName;
}
