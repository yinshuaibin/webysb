package com.ysb.service.impl;

import com.ysb.bean.CosmeticsStock;
import com.ysb.bean.dto.PageDTO;
import com.ysb.dao.CosmeticsStockDao;
import com.ysb.service.CosmeticsStockService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yinshuaibin
 * @date 2021/10/25 21:36
 * @description
 */
@Service
public class CosmeticsStockServiceImpl implements CosmeticsStockService {

    private CosmeticsStockDao cosmeticsStockDao;

    @Autowired
    public CosmeticsStockServiceImpl(CosmeticsStockDao cosmeticsStockDao){
        this.cosmeticsStockDao = cosmeticsStockDao;
    }

    /**
     *
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param startDate 进货时间
     * @param endDate 进货时间
     * @param stockType 货品类型
     * @return pageDto
     */
    @Override
    public Object findAllCosmeticsStock(int pageNum, int pageSize, String startDate, String endDate, String stockType) {
        if (pageNum < 0){
            return new HashMap<String, Object>(1){{put("total", 0);}};
        }
        // jpa分页查询默认从0开始
        pageNum = pageNum -1;
        Specification<CosmeticsStock> querySpecifi = (Specification<CosmeticsStock>) (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(stockType)){
                predicates.add(cb.equal(root.get("stockType").as(String.class), stockType));
            }
//            if (StringUtils.isNotBlank(name)){
//                predicates.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
//            }
            if (StringUtils.isNotBlank(startDate)){
                predicates.add(cb.greaterThanOrEqualTo(root.get("stockTime").as(String.class), startDate));
            }
            if (StringUtils.isNotBlank(endDate)){
                predicates.add(cb.lessThanOrEqualTo(root.get("stockTime").as(String.class), endDate));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return new PageDTO<>(cosmeticsStockDao.findAll(querySpecifi, PageRequest.of(pageNum, pageSize)));
    }
}
