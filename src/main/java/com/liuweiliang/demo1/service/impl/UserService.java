package com.liuweiliang.demo1.service.impl;


import com.baomidou.mybatisplus.extension.service.IService;
import com.liuweiliang.demo1.entity.model.UserModel;
import com.liuweiliang.demo1.entity.vo.UserVO;

import java.util.List;

public interface  UserService extends IService<UserModel> {

    List<UserVO> all();

    List<UserVO> show(Long id);

}
