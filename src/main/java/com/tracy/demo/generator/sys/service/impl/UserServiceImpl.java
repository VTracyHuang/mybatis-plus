package com.tracy.demo.generator.sys.service.impl;

import com.tracy.demo.generator.sys.entity.User;
import com.tracy.demo.generator.sys.mapper.UserMapper;
import com.tracy.demo.generator.sys.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
