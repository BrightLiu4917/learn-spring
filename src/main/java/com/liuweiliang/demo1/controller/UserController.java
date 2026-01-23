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

    @GetMapping(value = "/all")
    public Result<List<UserVO>> list() {
        List<UserVO> list = userService.all();
        return Result.success(list);
    }


  @GetMapping(value = "/show")
  public Result<List<UserVO>> show(@RequestParam Long id) throws Exception {
      List<UserVO> list = userService.show(id);
      return Result.success(list);
  }

}
