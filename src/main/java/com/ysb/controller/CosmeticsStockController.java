package com.ysb.controller;

import com.ysb.bean.CosmeticsStock;
import com.ysb.controller.base.BaseController;
import com.ysb.service.CosmeticsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yinshuaibin
 * @date 2021/10/25 21:35
 * @description
 */
@RestController
public class CosmeticsStockController extends BaseController {


    private CosmeticsStockService cosmeticsStockService;


    @Autowired
    public CosmeticsStockController(CosmeticsStockService cosmeticsStockService){
        this.cosmeticsStockService = cosmeticsStockService;
    }

    @PostMapping("/findAllCosmeticsStock")
    public Object findAllCosmeticsStock(@RequestBody Map reqMap){
        int pageNum = (int) reqMap.get("pageNum");
        int pageSize = (int) reqMap.get("pageSize");
        // 开始时间
        String startDate = (String) reqMap.get("startDate");
        // 结束时间
        String endDate = (String)reqMap.get("endDate");
        // 进货类型
        String stockType = (String)reqMap.get("stockType");
        return cosmeticsStockService.findAllCosmeticsStock(pageNum, pageSize, startDate, endDate, stockType);
    }

    @GetMapping("/deleteCosmeticsStock")
    public void delete(int id){
        cosmeticsStockService.deleteCosmeticsStock(id);
    }

    @PostMapping("/saveCosmeticsStock")
    public void saveCosmeticsStock(@RequestBody CosmeticsStock cosmeticsStock){
        cosmeticsStockService.saveCosmeticsStock(cosmeticsStock);
    }
}
