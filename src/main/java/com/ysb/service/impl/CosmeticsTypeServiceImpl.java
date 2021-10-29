package com.ysb.service.impl;

import com.ysb.bean.CosmeticsType;
import com.ysb.bean.dto.PageDTO;
import com.ysb.dao.CosmeticsTypeDao;
import com.ysb.service.CosmeticsTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * @author yinshuaibin
 * @date 2021/10/25 22:14
 * @description
 */
@Service
public class CosmeticsTypeServiceImpl implements CosmeticsTypeService {

    private CosmeticsTypeDao cosmeticsTypeDao;

    @Autowired
    public CosmeticsTypeServiceImpl(CosmeticsTypeDao cosmeticsTypeDao){
        this.cosmeticsTypeDao = cosmeticsTypeDao;
    }

    /**
     *
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param name 名称
     * @return pagDto
     */
    @Override
    public Object findAllCosmeticsType(int pageNum, int pageSize, String name) {
        if (pageNum < 0){
            return new HashMap<String, Object>(1){{put("total", 0);}};
        }
        // jpa分页查询默认从0开始
        pageNum = pageNum -1;
        Specification<CosmeticsType> querySpecifi = (Specification<CosmeticsType>) (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(name)){
                predicates.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return new PageDTO<>(cosmeticsTypeDao.findAll(querySpecifi, PageRequest.of(pageNum, pageSize)));
    }

    @Override
    public void saveCosmeticsType(CosmeticsType cosmeticsType) {
        int id = cosmeticsType.getId();
        if (id == 0){
            cosmeticsType.setTypeId(UUID.randomUUID().toString());
        }
        cosmeticsTypeDao.save(cosmeticsType);
    }

    /**
     *
     * @param id 主键id
     * @return 0成功, 1失败
     */
    @Override
    public int deleteType(Integer id) {
        cosmeticsTypeDao.deleteCosmeticsTypeById(id);
        return 1;
    }
}
