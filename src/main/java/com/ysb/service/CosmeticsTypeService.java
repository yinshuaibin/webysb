package com.ysb.service;

import com.ysb.bean.CosmeticsType;

/**
 * @author yinshuaibin
 * @date 2021/10/25 22:14
 * @description
 */
public interface CosmeticsTypeService {

    /**
     * 查询所有数据
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param name 名称
     * @return pagDto
     */
    Object findAllCosmeticsType(int pageNum, int pageSize, String name);

    /**
     * 添加/修改类型
     * @param cosmeticsType 类型
     */
    void saveCosmeticsType(CosmeticsType cosmeticsType);


    /**
     * 删除对应的化妆品信息
     * @param id 主键id
     * @return 0成功, 1失败
     */
    int deleteType(Integer id);
}
