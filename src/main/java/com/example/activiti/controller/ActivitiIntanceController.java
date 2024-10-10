package com.example.activiti.controller;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import com.example.activiti.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ActivitiIntanceController {

//    @Autowired
//    private ProcessInstance processInstance;

//    @Autowired
//    private TaskService taskService;

//    @Autowired
//    private RuntimeService runtimeService;

    @PostMapping("/project/deploy")
    public String deploy(@RequestBody LoginUser loginUser) {
//        runtimeService.startProcessInstanceByKey("admin");
        String s = loginUser.getPassword() + loginUser.getUsername();
        return "deploy: " + s;
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/project/get")
    public String start() {
        return "feafewa";
    }

    @ResponseBody
    @GetMapping("/project/color")
    public List<String> color() {
        List<String> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 3; i++) {
                sb.append(Math.random() * 100);
                if (i == 3) {
                    break;
                }
                sb.append(",");
            }
            list.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return list;
    }

}
