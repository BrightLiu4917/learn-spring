package com.liuweiliang.demo1.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuweiliang.demo1.entity.model.UserModel;

import java.util.List;

public interface UserMapper extends BaseMapper<UserModel> {

    List<UserModel> all();

    List<UserModel> show(Long id);

}
