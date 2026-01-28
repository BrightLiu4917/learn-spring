package com.liuweiliang.demo1.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.vo.AdminVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper extends BaseMapper<AdminModel> {

    List<AdminVO> all();

    List<AdminVO> show(String id);

    int insert(AdminModel admin);

    IPage<AdminVO> list(
            IPage<AdminVO> page,
            @Param(Constants.WRAPPER) Wrapper<AdminModel> wrapper // 关键：标记ew参数
    );
}
