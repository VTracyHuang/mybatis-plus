package com.tracy.demo.generator.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tracy.demo.generator.sys.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tracy
 * @since 2020-04-22
 */
public interface UsersMapper extends BaseMapper<Users> {

    @Select(("select * from users ${ew.customSqlSegment}"))
    List<Users> mySelectList(@Param(Constants.WRAPPER) Wrapper<Users> wrapper);
}
