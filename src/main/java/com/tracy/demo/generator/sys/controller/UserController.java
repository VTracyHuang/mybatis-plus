package com.tracy.demo.generator.sys.controller;


import com.tracy.demo.generator.sys.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tracy
 * @since 2020-04-22
 */
@RestController
@RequestMapping("/sys/user")
@Api(description = "用户操作接口")
public class UserController {

    @ApiOperation("测试json注入User对象")
    @PostMapping("/inject")
    @ResponseBody
    public User inject(User user)  {
        User user1 = new User();
        user1.setId(123456L);
        user1.setEmail("861522731@qq.com");
        user1.setAge(18);
        user1.setName("Tracy");
        return user1;
    }
   @ApiOperation("测试json注入User对象2")
   @PostMapping("/inject2")
   public void test2(){
       System.out.println("1");
   }
}
