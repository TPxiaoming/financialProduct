package com.xiaoming.manager.service;

import com.xiaoming.entity.Product;
import com.xiaoming.entity.enums.ProductStatus;
import com.xiaoming.manager.repositories.ProductRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 产品服务类
 */
@Service
public class ProductService {
    private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepositories productRepositories;

    /**
     * 创建产品
     * @param product
     * @return
     */
    public Product addProduct(Product product){
        LOG.debug("创建产品，参数：{}", product);
        //数据校验
        checkProduct(product);

        //设置默认值
        setDefault(product);

        Product result = productRepositories.save(product);

        LOG.debug("创建产品，结果{}", result);
        return null;
    }

    /**
     * 查询单个产品
     * @param id 产品编号
     * @return  返回对应产品或者null
     */
    public Product findOne(String id){
        Assert.notNull(id, "需要产品编号参数");
        LOG.debug("查询单个产品，  id={}", id);

        Product product = productRepositories.findById(id).orElse(null);
        LOG.debug("查询单个产品，结果={}", product);
        return product;
    }

    public Page<Product> query(List<String> idList,
                               BigDecimal minRewardRate, BigDecimal maxRewardRate,
                               List<String> statusList,
                               Pageable pageable){
        LOG.debug("查询产品，idList={}, minRewardRate={}, maxRewardRate={}, statusList={}, pageable={}",
                idList, minRewardRate, maxRewardRate, statusList, pageable);
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //获取对应列
                Expression<String> idCol = root.get("id");
                Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
                Expression<String> statusCol = root.get("status");

                List<Predicate> predicates = new ArrayList<>();
                if (idList != null && idList.size() > 0){
                    predicates.add(idCol.in(idList));
                }
                if (minRewardRate != null && BigDecimal.ZERO.compareTo(minRewardRate) < 0){
                    predicates.add(criteriaBuilder.ge(rewardRateCol, minRewardRate));
                }
                if (maxRewardRate != null && BigDecimal.ZERO.compareTo(maxRewardRate) < 0){
                    predicates.add(criteriaBuilder.le(rewardRateCol, maxRewardRate));
                }
                if (statusList != null && statusList.size() > 0){
                    predicates.add(statusCol.in(statusList));
                }
                query.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };
        Page<Product> page = productRepositories.findAll(specification, pageable);
        LOG.debug("查询产品结果，结果={}", page);
        return  page;
    }

    /**
     * 设置默认值
     * 创建时间
     * 更新时间
     * 投资步长
     * 锁定期
     * 状态
     * 为什么不在产品对象中写默认值呢？
     * 所有属性都要使用包装类，因为int有默认值，
     * 属性为0有可能是默认的，也有可能是我们设置的
     * 这就有歧义了
     * 指定值也会有歧义
     * 所以对象属性不指定值
     * @param product
     */
    private void setDefault(Product product) {
        if (product.getCreateAt() == null){
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt() == null){
            product.setUpdateAt(new Date());
        }
        if (product.getStepAmount() == null){
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm() == null){
            product.setLockTerm(0);
        }
        if (product.getStatus() == null){
            product.setStatus(ProductStatus.AUDINTING.name());
        }
    }

    /**
     * 产品数据校验
     * 1.非空数据
     * 2.收益率要0-30以内
     * 3.投资步长需为整数
     * @param product
     */
    private void checkProduct(Product product) {
        Assert.notNull(product.getId(), "编号不可为空");

        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) >= 0, "收益率返回错误");

        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0, "投资步长需为整数");
    }
}
