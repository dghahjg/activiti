package com.example.activiti.mapper;

import com.example.activiti.domain.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginUserMapper {

    LoginUser selectLoginUserByUserName(@Param("username") String username);
}
