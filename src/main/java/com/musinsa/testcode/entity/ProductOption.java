package com.musinsa.testcode.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@IdClass(ProductOptionPk.class)
@Table(name = "PRODUCT_OPTION")
@Entity
/*@Table(name="product_option", uniqueConstraints = {
        @UniqueConstraint(name="PRODUCT_OPTION_PK", columnNames = {"product_name", "option_name"})
})*/
public class ProductOption {

    @Id
    @Column(name = "PRODUCT_NAME")
    String productName;

    @Id
    @Column(name = "OPTION_NAME")
    String optionName;

    @Column(name = "PRODUCT_OPTION_CNT")
    int productOptionCnt;
}
