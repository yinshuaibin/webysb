package com.ysb.service;

/**
 * @author yinshuaibin
 * @date 2021/10/25 21:36
 * @description
 */
public interface CosmeticsStockService {

    /**
     * 查询所有进货
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param startDate 进货时间
     * @param endDate 进货时间
     * @param stockType 货品类型
     * @return pageDto
     */
    Object findAllCosmeticsStock(int pageNum, int pageSize, String startDate, String endDate, String stockType);
}
