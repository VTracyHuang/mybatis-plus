package com.tracy.demo.generator.sys.service.impl;

import com.tracy.demo.generator.sys.entity.Users;
import com.tracy.demo.generator.sys.mapper.UsersMapper;
import com.tracy.demo.generator.sys.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tracy
 * @since 2020-04-22
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
