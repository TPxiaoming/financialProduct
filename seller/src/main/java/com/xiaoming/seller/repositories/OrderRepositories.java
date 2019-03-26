package com.xiaoming.seller.repositories;

import com.xiaoming.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 订单管理
 */
public interface OrderRepositories extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
}
