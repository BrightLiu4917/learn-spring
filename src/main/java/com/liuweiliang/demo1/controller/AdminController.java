package com.liuweiliang.demo1.controller;

import com.liuweiliang.demo1.common.Result;
import com.liuweiliang.demo1.entity.vo.AdminVO;
import com.liuweiliang.demo1.service.impl.Admin.AdminService;
import com.liuweiliang.demo1.service.impl.Admin.AdminServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
@RestController
@RequestMapping("/admin")
public class AdminController {
    //注入AdminService
    @Resource
    AdminServiceImpl adminService;


    @GetMapping(value = "/list")
    public Object list(){
        System.out.println(adminService.all());
        return Result.success(adminService.all());
    }

}
