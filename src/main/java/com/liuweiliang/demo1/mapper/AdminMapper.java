package com.liuweiliang.demo1.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.vo.AdminVO;

import java.util.List;

public interface AdminMapper extends BaseMapper<AdminModel> {

    List<AdminVO> all();

    List<AdminVO> show(String id);

}
