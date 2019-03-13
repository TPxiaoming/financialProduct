package com.xiaoming.manager.repositories;

import com.xiaoming.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 产品管理
 */
public interface ProductRepositories extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
}
