package com.example.activiti.test;

import com.example.activiti.controller.ActivitiIntanceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestForMe {
//    private final static LoginUserMapper loginUserMapper = (LoginUserMapper) SpringUtils.getBean("username");

    @Autowired
    private ActivitiIntanceController activitiIntance;

    public void test() {
//        LoginUser loginUser = loginUserMapper.selectLoginUserByUserName("username");
//        System.out.println(loginUser);
//        String start = activitiIntance.start();
        first();
//        System.out.println(start);
    }

    private void first() {
        System.out.println("1");
    }

}
