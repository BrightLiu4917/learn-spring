package com.liuweiliang.demo1.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.vo.AdminVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface AdminMapper extends BaseMapper<AdminModel> {

    List<AdminVO> all();

    List<AdminVO> show(String id);


    /**
     * 插入管理员
     * @param admin 传入的管理员对象
     * @return 返回影响的行数 (1表示成功)
     */
    @Insert("INSERT INTO erp_admins " +
            "(account, email, password) " +
            "VALUES " +
            "(#{account}, #{email}, #{password})")

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(AdminModel admin);
}
