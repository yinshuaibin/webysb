package com.ysb.dao;

import com.ysb.bean.CosmeticsType;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yinshuaibin
 * @date 2021/10/25 22:13
 * @description
 */
@Repository
public interface CosmeticsTypeDao extends JpaRepositoryImplementation<CosmeticsType, Integer> {


    /**
     * 根据id删除对应的信息
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteCosmeticsTypeById(Integer id);

    /**
     * 根据id查询对应的类型信息
     * @param id id
     * @return 对应的化妆品信息
     */
    CosmeticsType findCosmeticsTypeById(Integer id);

}
