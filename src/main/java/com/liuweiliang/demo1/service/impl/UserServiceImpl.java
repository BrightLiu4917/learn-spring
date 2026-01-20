package com.liuweiliang.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuweiliang.demo1.entity.model.UserModel;
import com.liuweiliang.demo1.entity.vo.UserVO;
import com.liuweiliang.demo1.mapper.UserMapper;
import com.liuweiliang.demo1.service.impl.UserService;
import com.mysql.cj.log.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {

    private UserMapper userMapper;
    @Override
    public List<UserVO> all() {
        return baseMapper.all();
    }

    @Override
    public List<UserVO> show(Long id) {
        return baseMapper.show(id);
    }
}
