package com.xiaoming.api.events;

import com.xiaoming.entity.enums.ProductStatus;

import java.io.Serializable;

/**
 * 产品状态事件
 */
public class ProductStatusEvent implements Serializable {
    //产品编号
    private  String id;
    //产品状态
    private ProductStatus productStatus;

    public ProductStatusEvent(String id, ProductStatus productStatus) {
        this.id = id;
        this.productStatus = productStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "ProductStatusEvent{" +
                "id='" + id + '\'' +
                ", productStatus=" + productStatus +
                '}';
    }
}
