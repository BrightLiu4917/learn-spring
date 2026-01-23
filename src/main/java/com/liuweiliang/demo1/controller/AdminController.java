package com.liuweiliang.demo1.controller;

import com.liuweiliang.demo1.common.Result;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.req.AdminCreateRequest;
import com.liuweiliang.demo1.entity.vo.AdminVO;
import com.liuweiliang.demo1.service.impl.Admin.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/admin")
public class AdminController {
    //注入AdminService
    @Resource
    AdminServiceImpl adminService;


    @GetMapping(value = "/all")
    public Result<List<AdminVO>> all(){
        return Result.success(adminService.all());
    }


    @GetMapping(value = "/show/{id}")
    public Result<List<AdminVO>> show(){
        return Result.success(adminService.show("1"));
    }


    @PostMapping(value = "/create")
    public boolean create(@Valid @RequestBody AdminCreateRequest  request ){

        return true;
    }

}
