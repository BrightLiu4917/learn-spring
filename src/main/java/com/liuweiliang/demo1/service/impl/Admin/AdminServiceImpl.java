package com.liuweiliang.demo1.service.impl.Admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.vo.AdminVO;
import com.liuweiliang.demo1.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminModel> implements AdminService {

    @Override
    public List<AdminVO> all() {
        return baseMapper.all();
    }

    public List<AdminVO> show(String id) {
        return baseMapper.show(id);
    }
}
