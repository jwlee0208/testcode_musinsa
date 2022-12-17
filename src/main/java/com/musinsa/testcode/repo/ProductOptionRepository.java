package com.musinsa.testcode.repo;

import com.musinsa.testcode.entity.ProductOption;
import com.musinsa.testcode.entity.ProductOptionPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, ProductOptionPk> {

    public List<ProductOption> findAllByProductName(String productName);

    public Optional<ProductOption> findByProductNameAndOptionName(String productName, String optionName);

    public ProductOption save(ProductOption productOption);
}
