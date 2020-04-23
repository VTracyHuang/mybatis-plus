package com.tracy.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tracy.demo.generator.sys.entity.Users;
import com.tracy.demo.generator.sys.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {

    @Autowired
    private UsersMapper usersMapper;

    /*
    deleteById
     */

    @Test
    public void testById(){
        int rows = usersMapper.deleteById(1L);
        System.out.println("影响条数："+rows);
    }

    /*
    deleteByMap
     */
    @Test
    public void deleteByMap(){
        Map <String,Object> columnMap = new HashMap<>();
        columnMap.put("name","向后");
        int rows = usersMapper.deleteByMap(columnMap);
        System.out.println("影响条数："+rows);
    }

    /*
    deleteBatchIds
     */
    @Test
    public void deleteBatchIds() {
        int rows = usersMapper.deleteBatchIds(Arrays.asList(1L,2L,3L));
        System.out.println("影响条数："+rows);
    }

    /*
    deleteByWrapper
     */
    @Test
    public void deleteByWrapper() {
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Users::getAge,27).or().gt(Users::getAge,41);
        int rows = usersMapper.delete(lambdaQueryWrapper);
        System.out.println("影响条数："+rows);
    }
}
