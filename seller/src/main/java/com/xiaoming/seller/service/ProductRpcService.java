package com.xiaoming.seller.service;

import com.xiaoming.api.ProductRpc;
import com.xiaoming.api.domain.ProductRpcReq;
import com.xiaoming.api.events.ProductStatusEvent;
import com.xiaoming.entity.Product;
import com.xiaoming.entity.enums.ProductStatus;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品相关服务
 */
@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);

    static final String MQ_DESTINATION = "Consumer.cache.VirtualTopic.PRODUCT_STATUS";

    @Autowired
    private ProductRpc productRpc;

    @Autowired
    private ProductCache productCache;

    /**
     * 查询全部产品
     * @return
     */
    public List<Product> findAll(){
        return productCache.readAllCache();
    }

    /**
     * 查询单个产品
     * @param id
     * @return
     */
    public Product findOne(String id){
        Product product = productCache.readCache(id);
        if (product == null){
            productCache.removeCache(id);
        }
        return product;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Product> products = findAll();
        products.forEach(product -> {
            productCache.putCache(product);
        });
    }

    /**
     * 状态改变事件
     * @param event
     */
    void updataCache(ProductStatusEvent event){
        LOG.info("receive event:{}", event);
        //清空缓存
        productCache.removeCache(event.getId());
        //销售中，重新读取
        if (ProductStatus.IN_SELL.equals(event.getProductStatus())){
            productCache.readCache(event.getId());
        }
    }

    /*@PostConstruct
    public void testFindAll(){
        findAll();
    }*/
   /* @PostConstruct
    public void testFindOne(){
        findOne("001");
    }*/
}
