package com.liuweiliang.demo1.controller;

import com.liuweiliang.demo1.entity.vo.UserVO;
import com.liuweiliang.demo1.service.impl.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping(value = "/list")
    public void list() {
        System.out.println(userService.all());
    }


    @GetMapping(value = "/show")
    public void show(
            @RequestParam(required = true) String id) {
        Long userId = Long.parseLong(id);
        System.out.println(userService.show(userId)

        );
    }

}
