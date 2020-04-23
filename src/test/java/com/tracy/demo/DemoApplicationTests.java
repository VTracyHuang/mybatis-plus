package com.tracy.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tracy.demo.generator.sys.entity.User;
import com.tracy.demo.generator.sys.entity.Users;
import com.tracy.demo.generator.sys.mapper.UserMapper;
import com.tracy.demo.generator.sys.mapper.UsersMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Wrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Test
    void contextLoads() {

    }
    @Test
    public void testSelect() {
        System.out.println("----- selectAll methos test -----");
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5,userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void deleteById(){
        int rows = usersMapper.deleteById("1094592041087729666");
        System.out.println("影响行数"+rows);
    }

    @Test
    public void selectAll(){
        List<Users> list = usersMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void updateById(){
     //   Users users = usersMapper.selectById("1087982257332887553");
        Users users = new Users();
        users.setId(1087982257332887553L);
        users.setName("haha");
        int rows = usersMapper.updateById(users);
        System.out.println("影响行数"+rows);
    }

    @Test
    public void mySelect(){
        List<Users> list = usersMapper.mySelectList(Wrappers.<Users>lambdaQuery()
                .gt(Users::getAge,25).eq(Users::getDeleted,0));
        list.forEach(System.out::println);
    }

    @Test
    public void selectIds(){
        List<Long> idsList = Arrays.asList(1087982257332887553L,1088248166370832385L,1088250446457389058L);
        List<Users> usersList = usersMapper.selectBatchIds(idsList);
        usersList.forEach(System.out::println);
    }

    @Test
    public void selectByMap(){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("age",27);
        List<Users> usersList = usersMapper.selectByMap(columnMap);
        usersList.forEach(System.out::println);
    }
    /*
    名字里包含雨，且年龄小于40
     */
    @Test
    public void selectByWrapper(){
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>();
        queryWrraper.like("name","雨").lt("age",40);
        List<Users> usersList = usersMapper.selectList(queryWrraper);
        usersList.forEach(System.out::println);
    }
    /*
    名字里包含雨，且年龄在20-40岁之间，并且邮箱不为空
     */
    @Test
    public void selectByWrapper2(){
        QueryWrapper<Users> queryWrapper2 = Wrappers.<Users>query();
        queryWrapper2.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<Users> usersList = usersMapper.selectList(queryWrapper2);
        usersList.forEach(System.out::println);
    }

    /*
    名字为王姓，或者年龄大于等于25，按照年龄降序排序，年龄相同按照id升序排列
     */
    @Test
    public void selectByWrapper3(){
        QueryWrapper<Users> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.likeRight("name","王").or().ge("age",25).orderByDesc("age").orderByAsc("id");
        List<Users> usersList = usersMapper.selectList(queryWrapper3);
        usersList.forEach(System.out::println);
    }
    /*
    创建日期为2019年2月14日，并且直属上级的名字为王姓
     */
    @Test
    public void selectByWrapper4(){
        QueryWrapper<Users> queryWrapper4 = new QueryWrapper<>();
//        queryWrapper4.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
//        .inSql("manager_id","select id from users where name like '王%'");
        /*queryWrapper4.apply("date_format(create_time,'%Y-%m-%d')='2019-02-14'")
                .inSql("manager_id","select id from users where name like '王%'");  //存在sql注入的风险
        List<Users> usersList = usersMapper.selectList(queryWrapper4); */
        queryWrapper4.apply("date_format(create_time,'%Y-%m-%d')='2019-02-14' or true or true")
                .inSql("manager_id","select id from users where name like '王%'");  //存在sql注入的风险
        List<Users> usersList = usersMapper.selectList(queryWrapper4);
        usersList.forEach(System.out::println);
    }
    /*
    名字为王姓并且（年龄<40或者邮箱不为空)
     */
    @Test
    public void selectByWrapper5(){
        QueryWrapper<Users> queryWrapper5 = new QueryWrapper<>();
        queryWrapper5.likeRight("name","王").and(wq -> wq.lt("age",40).or().isNotNull("email"));
        List<Users> usersList = usersMapper.selectList(queryWrapper5);
        usersList.forEach(System.out::println);
    }
    /*
    名字为王姓或者(年龄小于40并且年两大于20并且邮箱不为空)
     */
    @Test
    public void selectByWrapper6(){
        QueryWrapper<Users> queryWrapper6 = new QueryWrapper<>();
        queryWrapper6.likeRight("name","王").or(wq -> wq.between("age",20,40).isNotNull("email"));

        List<Users> usersList = usersMapper.selectList(queryWrapper6);
        usersList.forEach(System.out::println);
    }
    /*
    年龄小于40或者邮箱不为空 并且名字为王姓
     */
    @Test
    public void selectByWrapper7(){
        QueryWrapper<Users> queryWrapper7 = new QueryWrapper<>();
        queryWrapper7.nested(wq -> wq.lt("age",40).or().isNotNull("email")).likeRight("name","王");
        List<Users> usersList = usersMapper.selectList(queryWrapper7);
        usersList.forEach(System.out::println);
    }

    /*
        年龄为30,31,34,35

     */
    @Test
    public void selectByWrapper8(){
        QueryWrapper<Users> queryWrapper8 = new QueryWrapper<>();
        queryWrapper8.in("age",Arrays.asList(30,31,34,35));
        List<Users> usersList = usersMapper.selectList(queryWrapper8);
        usersList.forEach(System.out::println);
    }
    /*
    只返回满足条件的其中一条语句即可
     */
    @Test
    public void selectByWrapper9(){
        QueryWrapper<Users> queryWrapper9 = new QueryWrapper<>();
        queryWrapper9.in("age",Arrays.asList(30,31,34,35)).last("limit 1");
        List<Users> usersList = usersMapper.selectList(queryWrapper9);
        usersList.forEach(System.out::println);
    }

    /*
名字里包含雨，且年龄小于40
 */
    @Test
    public void selectByWrapperSupper(){
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>();
       // queryWrraper.select("id","name").like("name","雨").lt("age",40);
        queryWrraper.like("name","雨").lt("age",40)
                .select(Users.class,info ->
                !info.getColumn().equals("create_time")&&
                        !info.getColumn().equals("manager_id"));
        List<Users> usersList = usersMapper.selectList(queryWrraper);
        usersList.forEach(System.out::println);
    }

    private void condition(String name,String email){
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>();
/*        if(StringUtils.isNotBlank(name)){
            queryWrraper.like("name",name);
        }
        if(StringUtils.isNotBlank(email)){
            queryWrraper.like("email",email);
        }*/
        queryWrraper.like(StringUtils.isNotBlank(name),"name",name)
                .like(StringUtils.isNotBlank(email),"email",email);
        List<Users> usersList = usersMapper.selectList(queryWrraper);
        usersList.forEach(System.out::println);
    }

    @Test
    public void testCondition(){
        String name = "王";
        String email = "";
        condition(name,email);
    }
    /*
    以实体为参数的构造器
     */
    @Test
    public void selectByWrapperEntity(){
        Users whereUsers = new Users();
        whereUsers.setName("刘红雨");
        whereUsers.setAge(32);
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>(whereUsers);

        List<Users> usersList = usersMapper.selectList(queryWrraper);
        usersList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperAllEq(){
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>();
        Map<String,Object> params = new HashMap<>();
        params.put("name","王天风");
        params.put("age",null);
      //  queryWrraper.allEq(params,false);
        queryWrraper.allEq((k,v)-> !k.equals("name"),params);
        List<Users> usersList = usersMapper.selectList(queryWrraper);
        usersList.forEach(System.out::println);
    }
    @Test
    public void selectByWrapperMaps(){
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>();

        List<Map<String,Object>> usersList = usersMapper.selectMaps(queryWrraper);
        usersList.forEach(System.out::println);
    }
    /*
    按照直属上级分组，查询每组的平均年龄，最大年龄，最小年龄
     */
    @Test
    public void selectByWrapperMaps2(){
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>();
        queryWrraper.select("avg(age) avg_age","min(age) min_age","max(age) max_age")
                .groupBy("manager_id").having("sum(age)<{0}",500);
        List<Map<String,Object>> usersList = usersMapper.selectMaps(queryWrraper);
        usersList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperObjs(){
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>();
        queryWrraper.select("id","name").like("name","雨").lt("age",40);
        List<Object> usersList = usersMapper.selectObjs(queryWrraper);
        usersList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperCount(){
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>();
        queryWrraper.like("name","雨").lt("age",40);
        Integer count= usersMapper.selectCount(queryWrraper);
        System.out.println("总记录数"+count);
        //总记录数1
    }
    @Test
    public void selectByWrapperOne(){
        QueryWrapper<Users> queryWrraper = new QueryWrapper<>();
        queryWrraper.like("name","雨").lt("age",40);
        Users users= usersMapper.selectOne(queryWrraper);
        System.out.println(users);

    }

    /**
     * lambda表达式可以很好的防误写
     */
    @Test
    public void selectByLambda(){
        //LambdaQueryWrapper <Users> lambda = new QueryWrapper<Users>().lambda();
        // LambdaQueryWrapper <Users> lambdaQueryWrapper = new LambdaQueryWrapper<Users>();
        LambdaQueryWrapper<Users> lambdaQuery = Wrappers.<Users>lambdaQuery();
        lambdaQuery.like(Users::getName,"雨").lt(Users::getAge,40);
        List<Users> usersList = usersMapper.selectList(lambdaQuery);
        usersList.forEach(System.out::println);
    }
    /*
    名字为王姓，并且（年龄小于40或者邮箱不为空)
     */
    @Test
    public void selectByLambda2(){

        LambdaQueryWrapper<Users> lambdaQuery = Wrappers.<Users>lambdaQuery();
        lambdaQuery.likeRight(Users::getName,"王")
                .and(lqw ->lqw.lt(Users::getAge,40).or().isNotNull(Users::getEmail));
        List<Users> usersList = usersMapper.selectList(lambdaQuery);
        usersList.forEach(System.out::println);
    }

    @Test
    public void selectByLambda3(){

        List<Users> usersList = new LambdaQueryChainWrapper<Users>(usersMapper)
                .like(Users::getName, "雨").ge(Users::getAge, 20).list();
        usersList.forEach(System.out::println);
    }

    @Test
    public void selectPage(){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.ge("age",26);

        Page<Users> page = new Page<>(2,2);
        IPage<Users> iPage = usersMapper.selectPage(page,queryWrapper);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总记录数："+iPage.getTotal());
        List<Users> usersList = iPage.getRecords();
        usersList.forEach(System.out::println);
    }

    @Test
    public void selectPage2(){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.ge("age",26);

        Page<Users> page = new Page<Users>(1,2);

       /* IPage<Map<String, Users>> iPage = usersMapper.selectMapsPage(page,queryWrapper);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总记录数："+iPage.getTotal());
        List<Map<String, Users>> usersList = iPage.getRecords();
        usersList.forEach(System.out::println);
*/
    }
}
