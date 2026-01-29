package com.liuweiliang.demo1.service.impl.Admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.req.AdminCreateRequest;
import com.liuweiliang.demo1.entity.vo.AdminVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService extends IService<AdminModel> {

    // 查询所有
    List<AdminVO> all();

    // 查询单个
    List<AdminVO> show(String id);

    boolean createAdmin(AdminCreateRequest request);

    IPage<AdminVO> list(Integer pageNum, Integer pageSize, QueryWrapper<AdminModel> wrapper);


    AdminModel getAdminByUsername(String account,String password);
}