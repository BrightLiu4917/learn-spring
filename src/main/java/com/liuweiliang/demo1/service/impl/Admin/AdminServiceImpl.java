package com.liuweiliang.demo1.service.impl.Admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.req.AdminCreateRequest;
import com.liuweiliang.demo1.entity.vo.AdminVO;
import com.liuweiliang.demo1.mapper.AdminMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminModel> implements AdminService {

    @Override
    public List<AdminVO> all() {
        return baseMapper.all();
    }

    @Override
    public List<AdminVO> show(String id) {
        return baseMapper.show(id);
    }


    @Override
    public boolean createAdmin(AdminCreateRequest  request ) {
        AdminModel adminModel = new AdminModel();
        BeanUtils.copyProperties(request, adminModel);
        return this.save(adminModel);
    }

    @Override
    public IPage<AdminVO> list(Integer pageNum, Integer pageSize, QueryWrapper<AdminModel> wrapper) {
        // 1. 创建分页对象
        IPage<AdminVO> page = new Page<>(pageNum, pageSize);

        // 2. 关键：调用Mapper时，必须传入page + wrapper两个参数（匹配Mapper方法）
        // 即使无条件，也要传空的wrapper（比如 QueryWrapper<AdminModel> wrapper = new QueryWrapper<>()）
        return baseMapper.list(page, wrapper);
    }

}
