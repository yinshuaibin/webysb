package com.ysb.service;

import com.ysb.bean.CosmeticsStock;

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

    /**
     * 新增
     * @param cosmeticsStock 实体类
     */
    void saveCosmeticsStock(CosmeticsStock cosmeticsStock);

    /**
     * 删除
     * @param id 删除
     */
    void deleteCosmeticsStock(int id);
}
