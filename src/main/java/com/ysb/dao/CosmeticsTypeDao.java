package com.ysb.dao;

import com.ysb.bean.CosmeticsType;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @author yinshuaibin
 * @date 2021/10/25 22:13
 * @description
 */
@Repository
public interface CosmeticsTypeDao extends JpaRepositoryImplementation<CosmeticsType, Integer> {
}
