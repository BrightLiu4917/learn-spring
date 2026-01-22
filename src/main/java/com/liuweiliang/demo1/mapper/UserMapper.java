package com.liuweiliang.demo1.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuweiliang.demo1.entity.model.UserModel;
import com.liuweiliang.demo1.entity.vo.UserVO;

import java.util.List;

public interface UserMapper extends BaseMapper<UserModel> {

    List<UserVO> all();

    List<UserVO> show(String id);

}
