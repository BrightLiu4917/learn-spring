package com.liuweiliang.demo1.service.impl.User;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuweiliang.demo1.entity.model.UserModel;
import com.liuweiliang.demo1.entity.vo.UserVO;
import com.liuweiliang.demo1.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserVO> all() {
        List<UserModel> models = baseMapper.all();
        List<UserVO> vos = new ArrayList<>();
        if (models != null) {
            for (UserModel m : models) {
                UserVO vo = new UserVO();
                BeanUtils.copyProperties(m, vo);
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<UserVO> show(Long id) {
        List<UserModel> models = baseMapper.show(id);
        List<UserVO> vos = new ArrayList<>();
        if (models != null) {
            for (UserModel m : models) {
                UserVO vo = new UserVO();
                BeanUtils.copyProperties(m, vo);
                vos.add(vo);
            }
        }
        return vos;
    }
}
