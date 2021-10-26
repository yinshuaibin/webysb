package com.ysb.dao;

import com.ysb.bean.CosmeticsStock;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @author yinshuaibin
 * @date 2021/10/24 14:56
 * @description
 */
@Repository
public interface CosmeticsStockDao extends JpaRepositoryImplementation<CosmeticsStock, Integer> {

}
