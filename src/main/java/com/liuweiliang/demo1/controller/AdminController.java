package com.liuweiliang.demo1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liuweiliang.demo1.common.Result;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.req.AdminCreateRequest;
import com.liuweiliang.demo1.entity.vo.AdminVO;
import com.liuweiliang.demo1.service.impl.Admin.AdminServiceImpl;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Data
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
    public Result<List<AdminVO>> show(@PathVariable String id){
        return Result.success(adminService.show(id));
    }


    @PostMapping(value = "/create")
    public Result<Boolean> create(@Valid @RequestBody AdminCreateRequest  request ){
        boolean isInsert = adminService.createAdmin(request);
        return isInsert ? Result.success() : Result.fail(500,"插入数据失败");
    }

    // 分页查询所有管理员：接收pageNum（页码）、pageSize（页大小）
    @GetMapping("/list")
    public Result<IPage<AdminVO>> pageAll(
            // @RequestParam：接收URL参数，设置默认值避免空指针
            @RequestParam(defaultValue = "20") Integer pageNum,
            @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(required = false) String account // 可选：自定义筛选条件

    ) {



        // 调用Service分页方法
        QueryWrapper<AdminModel> wrapper = new QueryWrapper<>();
        if (account != null && !account.isEmpty()) {
            wrapper.like("account", "%"+account+"%"); // 拼接 WHERE account LIKE '%xxx%'
        }

        // 2. 调用Service（传递pageNum、pageSize、wrapper）
        IPage<AdminVO> pageResult = adminService.list(pageNum, pageSize, wrapper);
        return Result.success(pageResult);
    }

}
