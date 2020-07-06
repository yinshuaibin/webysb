//package com.ysb.auth;
//
//import com.ferret.auth.domain.Department;
//import com.ferret.auth.service.DepartmentService;
//import com.ferret.controller.BaseController;
//import com.github.pagehelper.Page;
//import org.apache.ibatis.annotations.Param;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author yinshuaibin
// * @date 2020/6/29 10:56
// */
//@RestController
//public class DepartmentController extends BaseController {
//
//    @Resource
//    private DepartmentService departmentService;
//
//    @RequestMapping("/findAllDepartment")
//    public Map findAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize){
//        Page<Department> all = departmentService.findAll(pageNum, pageSize);
//        Map result = new HashMap();
//        result.put("resultList", all.getResult());
//        result.put("totalNum", all.getTotal());
//        return result;
//    }
//
//    @RequestMapping("/findById")
//    public Department findById(int id){
//        return departmentService.findById(id);
//    }
//
//    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
//    public void addDepartment(@RequestBody Department department){
//        departmentService.addDepartment(department);
//    }
//
//    @RequestMapping(value = "/editDepartment", method = RequestMethod.POST)
//    public void editDepartment(@RequestBody Department department){
//        departmentService.editDepartment(department);
//    }
//
//    @RequestMapping("/delDepartment")
//    public void delDepartment(int id){
//        departmentService.delDepartment(id);
//    }
//
//    @RequestMapping("/checkDepartmentName")
//    public int checkName(String name, Integer id){
//        return departmentService.checkName(id, name);
//    }
//}
