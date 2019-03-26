package com.xiaoming.manager.service;

import com.xiaoming.api.events.ProductStatusEvent;
import com.xiaoming.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 管理产品状态
 */
@Component
public class ProductStatusManager {
    static final String MQ_DESTINATION = "VirtualTopic.PRODUCT_STATUS";

    private static Logger LOG = LoggerFactory.getLogger(ProductStatusManager.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void changeStatus(String id, ProductStatus productStatus){
        ProductStatusEvent event = new ProductStatusEvent(id, productStatus);
        LOG.info("send message:{}", event);
        jmsTemplate.convertAndSend(MQ_DESTINATION, event);
    }

    @PostConstruct
    public void init(){
        changeStatus("001", ProductStatus.FINISHED);
    }
}
