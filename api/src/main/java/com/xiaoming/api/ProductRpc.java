package com.xiaoming.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.xiaoming.api.domain.ProductRpcReq;
import com.xiaoming.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 产品相关的rpc服务
 */
@JsonRpcService("rpc/products")
public interface ProductRpc {

    /**
     * 查询多个产品
     *  接口的参数不是固定的，我们可以把参数封装成一个对象
     * @param req
     * @return
     */
    List<Product> query(ProductRpcReq req);

    /**
     * 查询单个产品
     *
     * @param id
     * @return
     */
    Product findOne(String id);
}
