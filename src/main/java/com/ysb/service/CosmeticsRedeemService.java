package com.ysb.service;

import com.ysb.bean.CosmeticsRedeem;

/**
 * 兑奖
 * @author yinshuaibin
 * @date 2021/10/25 21:36
 * @description
 */
public interface CosmeticsRedeemService {

    /**
     * 查询所有进货
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param startDate 兑奖时间
     * @param endDate 更新时间
     * @param redeemType 化妆品类型
     * @return pageDto
     */
    Object findAllCosmeticsRedeem(int pageNum, int pageSize, String startDate, String endDate, String redeemType);

    /**
     * 保存和更新
     * @param cosmeticsRedeem
     */
    void saveOrUpdateCosmeticsRedeem(CosmeticsRedeem cosmeticsRedeem);

    /**
     * 删除
     * @param id
     */
    void deleteCosmeticsRedeem(int id);
}
