package com.tracy.demo;

import com.tracy.demo.generator.sys.entity.Users;
import com.tracy.demo.generator.sys.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class OptTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    void contextLoads() {

    }

    @Test
    public void insert(){
        Users users = new Users();
        users.setName("刘明超");
        users.setAge(31);
        users.setEmail("IFD@hu.com");
        users.setManagerId(1087982257332887553L);
        int rows = usersMapper.insert(users);
        System.out.println("影响行数："+users);
    }

    @Test
    public void updateById(){
     //   Users users = usersMapper.selectById("1087982257332887553");
        int version = 1;
        Users users = new Users();
        users.setId(1087982257332887553L);
        users.setEmail("ly@baomi.com");
        users.setVersion(version);
        int rows = usersMapper.updateById(users);
        System.out.println("影响行数"+rows);
    }
    @Test
    public void updateById2(){
        //   Users users = usersMapper.selectById("1087982257332887553");
        int version = 2;
        Users users = new Users();
        users.setId(1087982257332887553L);
        users.setEmail("ly@baomi.com");
        users.setVersion(version);
        int rows = usersMapper.updateById(users);
        System.out.println("影响行数"+rows);
    }
}
