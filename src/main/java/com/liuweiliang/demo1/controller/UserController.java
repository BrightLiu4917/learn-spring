package com.liuweiliang.demo1.controller;

import com.liuweiliang.demo1.common.Result;
import com.liuweiliang.demo1.entity.vo.UserVO;
import com.liuweiliang.demo1.service.impl.User.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 列表
     * @return Object
     */
    @GetMapping(value = "/list")
    public Object list() {
        return Result.success(userService.all());
    }


  // java    public ResultWrapper<List<ComponentInfoDTO>> list() throws Exception {
  @GetMapping(value = "/show")
  public Result<List<UserVO>> show(@RequestParam String id) throws Exception {
      List<UserVO> list = userService.show(id);
      return Result.success(list); // 统一包装为 { code, message, data }
  }

}
