package com.example.activiti.controller;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ActivitiIntance {

//    @Autowired
//    private ProcessInstance processInstance;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @GetMapping("/project/deploy")
    public void deploy() {
        runtimeService.startProcessInstanceByKey("admin");
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/project/get")
    public String start() {
        return "feafewa";
    }

}
