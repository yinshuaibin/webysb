package com.ysb.controller;

import com.ysb.bean.CosmeticsType;
import com.ysb.controller.base.BaseController;
import com.ysb.service.CosmeticsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yinshuaibin
 * @date 2021/10/25 22:12
 * @description
 */
@RestController
public class CosmeticsTypeController extends BaseController {

    private CosmeticsTypeService cosmeticsTypeService;

    @Autowired
    public CosmeticsTypeController(CosmeticsTypeService cosmeticsTypeService){
        this.cosmeticsTypeService = cosmeticsTypeService;
    }

    @PostMapping("/findAllCosmeticsType")
    public Object findAllCosmeticsType(@RequestBody Map reqMap){
        int pageNum = (int) reqMap.get("pageNum");
        int pageSize = (int) reqMap.get("pageSize");
        // 名称
        String name = (String) reqMap.get("name");
        return cosmeticsTypeService.findAllCosmeticsType(pageNum, pageSize, name);
    }

    @PostMapping("/saveCosmeticsType")
    public void saveCosmeticsType(@RequestBody CosmeticsType cosmeticsType){
        cosmeticsTypeService.saveCosmeticsType(cosmeticsType);
    }


    @GetMapping("/deleteType")
    public Object deleteType(Integer id){
        return cosmeticsTypeService.deleteType(id);
    }
}
