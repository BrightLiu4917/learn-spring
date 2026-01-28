package com.liuweiliang.demo1.service.impl.Admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.req.AdminCreateRequest;
import com.liuweiliang.demo1.entity.vo.AdminVO;
import com.liuweiliang.demo1.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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


    public int createAdmin(AdminModel admin) {

        int rows = baseMapper.insert(admin);
        if (rows > 0) {
            return 1;
        }
        return 0;
    }

}
