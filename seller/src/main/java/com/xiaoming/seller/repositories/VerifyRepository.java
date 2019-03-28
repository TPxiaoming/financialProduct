package com.xiaoming.seller.repositories;

import com.xiaoming.entity.VerificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * 对账相关
 */
public interface VerifyRepository extends JpaRepository<VerificationOrder, String>, JpaSpecificationExecutor<VerificationOrder> {
    /**
     * 查询某段时间(start,end)内的某个渠道chanId的对账数据
     * @param chanId
     * @param start
     * @param end
     * @return 对账数据列表
     */
    @Query(value = "SELECT CONCAT_WS('|',order_id,outer_order_id,chan_id,chan_user_id,product_id,order_type,amount,DATE_FORMAT(create_at,'%Y-%m-%d %H:%i:%s')) FROM order_t WHERE order_status = 'success' AND chan_id = ?1 AND create_at >= ?2 AND create_at <= ?3", nativeQuery = true)
    List<String> queryVerificationOrders(String chanId, Date start, Date end);
}
