package com.ysb.service.impl;

import com.ysb.bean.CosmeticsRedeem;
import com.ysb.bean.dto.PageDTO;
import com.ysb.dao.CosmeticsRedeemDao;
import com.ysb.service.CosmeticsRedeemService;
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
public class CosmeticsRedeemServiceImpl implements CosmeticsRedeemService {

    private CosmeticsRedeemDao cosmeticsRedeemDao;

    @Autowired
    public CosmeticsRedeemServiceImpl(CosmeticsRedeemDao cosmeticsRedeemDao){
        this.cosmeticsRedeemDao = cosmeticsRedeemDao;
    }

    /**
     * 分页查询所有兑奖数据
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param startDate 兑奖时间
     * @param endDate 更新时间
     * @param redeemType 化妆品类型
     * @return
     */
    @Override
    public Object findAllCosmeticsRedeem(int pageNum, int pageSize, String startDate, String endDate, String redeemType) {
        if (pageNum < 0){
            return new HashMap<String, Object>(1){{put("total", 0);}};
        }
        // jpa分页查询默认从0开始
        pageNum = pageNum -1;
        Specification<CosmeticsRedeem> querySpecifi = (Specification<CosmeticsRedeem>) (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(redeemType)){
                predicates.add(cb.equal(root.get("redeemType").as(String.class), redeemType));
            }
            if (StringUtils.isNotBlank(startDate)){
                predicates.add(cb.greaterThanOrEqualTo(root.get("redeemTime").as(String.class), startDate));
            }
            if (StringUtils.isNotBlank(endDate)){
                predicates.add(cb.lessThanOrEqualTo(root.get("redeemTime").as(String.class), endDate));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return new PageDTO<>(cosmeticsRedeemDao.findAll(querySpecifi, PageRequest.of(pageNum, pageSize)));
    }

    /**
     * 保存和更新操作
     * @param cosmeticsRedeem
     */
    @Override
    public void saveOrUpdateCosmeticsRedeem(CosmeticsRedeem cosmeticsRedeem) {
        cosmeticsRedeemDao.save(cosmeticsRedeem);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void deleteCosmeticsRedeem(int id) {
        cosmeticsRedeemDao.deleteById(id);
    }

}
