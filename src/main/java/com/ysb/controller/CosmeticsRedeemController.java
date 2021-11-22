package com.ysb.controller;

import com.ysb.bean.CosmeticsRedeem;
import com.ysb.controller.base.BaseController;
import com.ysb.service.CosmeticsRedeemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 兑奖菜单
 * @author yinshuaibin
 * @date 2021/10/25 21:35
 * @description
 */
@RestController
public class CosmeticsRedeemController extends BaseController {

    private CosmeticsRedeemService cosmeticsRedeemService;

    @Autowired
    public CosmeticsRedeemController(CosmeticsRedeemService cosmeticsRedeemService){
        this.cosmeticsRedeemService = cosmeticsRedeemService;
    }

    /**
     * @desc 分页查询所有
     * @author yb
     * @createTime 2021/11/2 22:42
     * @param reqMap	 
     * @return 
     */
    @PostMapping("/findAllCosmeticsRedeem")
    public Object findAllCosmeticsRedeem(@RequestBody Map reqMap){
        int pageNum = (int) reqMap.get("pageNum");
        int pageSize = (int) reqMap.get("pageSize");
        // 开始时间
        String startDate = (String) reqMap.get("startDate");
        // 结束时间
        String endDate = (String)reqMap.get("endDate");
        // 化妆品类型
        String redeemType = (String)reqMap.get("redeemType");
        return cosmeticsRedeemService.findAllCosmeticsRedeem(pageNum, pageSize, startDate, endDate, redeemType);
    }

    @PostMapping("/saveOrUpdateCosmeticsRedeem")
    public void saveOrUpdateCosmeticsRedeem(@RequestBody CosmeticsRedeem cosmeticsRedeem){
        cosmeticsRedeemService.saveOrUpdateCosmeticsRedeem(cosmeticsRedeem);
    }

    @GetMapping("/deleteCosmeticsRedeem")
    public void deleteObject(int id) {
        cosmeticsRedeemService.deleteCosmeticsRedeem(id);
    }

}
