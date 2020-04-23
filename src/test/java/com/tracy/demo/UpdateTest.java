package com.tracy.demo;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tracy.demo.generator.sys.entity.User;
import com.tracy.demo.generator.sys.entity.Users;
import com.tracy.demo.generator.sys.mapper.UserMapper;
import com.tracy.demo.generator.sys.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void updateById(){
        Users users = new Users();
        users.setId(1088248166370832385l);
        users.setAge(26);
        users.setEmail("wtf2@baomidou.com");
        int rows = usersMapper.updateById(users);
        System.out.println("影响记录数"+rows);
    }
    @Test
    public void updateByWrapper() {
        UpdateWrapper<Users> updateWrapper = new UpdateWrapper<Users>();
        updateWrapper.eq("name","李艺伟").eq("age",28);
        Users users = new Users();
        users.setEmail("lyw2019@baomidou.com");
        users.setAge(29);
        int rows = usersMapper.update(users,updateWrapper);
        System.out.println("影响记录数："+rows);
    }
}
