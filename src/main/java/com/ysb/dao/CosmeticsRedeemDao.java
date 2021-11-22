package com.ysb.dao;

import com.ysb.bean.CosmeticsRedeem;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * 兑奖
 * @author yinshuaibin
 * @date 2021/10/24 14:56
 * @description
 */
@Repository
public interface CosmeticsRedeemDao extends JpaRepositoryImplementation<CosmeticsRedeem, Integer> {

}
