package com.example.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ProcessInstanceTest {

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 初始化流程实例
     */
    @Test
    public void initProcessInstance() {
        // 流程定义KEY
        String processDefinitionKey = "Part1_Deployment";
        // 业务表KEY（用于把业务数据与Activiti7流程数据相关联）
        String businessKey = "4208169753200945";
        // 参数
        Map<String, Object> variables = new HashMap<>(16);
        ProcessInstance processInstance = this.runtimeService
                .startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        System.out.println("流程实例ID：" + processInstance.getProcessInstanceId());
    }

    /**
     * 查询流程实例
     */
    @Test
    public void getProcessInstance() {
        String processInstanceId = "354709ac-477f-11ed-abfa-e4a8dfd43d4a";
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        System.out.println("ProcessInstanceId：" + processInstance.getProcessInstanceId());
        System.out.println("ProcessDefinitionId：" + processInstance.getProcessDefinitionId());
        System.out.println("isEnded：" + processInstance.isEnded());
        System.out.println("isSuspended：" + processInstance.isSuspended());
    }

    /**
     * 查询流程实例列表
     */
    @Test
    public void listProcessInstances() {
        List<ProcessInstance> processInstanceList = this.runtimeService.createProcessInstanceQuery().list();
        if (!CollectionUtils.isEmpty(processInstanceList)) {
            processInstanceList.forEach(processInstance -> {
                System.out.println("ProcessInstanceId：" + processInstance.getProcessInstanceId());
                System.out.println("ProcessDefinitionId：" + processInstance.getProcessDefinitionId());
                System.out.println("isEnded：" + processInstance.isEnded());
                System.out.println("isSuspended：" + processInstance.isSuspended());
            });
        }
    }

    /**
     * 挂起流程实例
     */
    @Test
    public void suspendProcessInstance() {
        String processInstanceId = "354709ac-477f-11ed-abfa-e4a8dfd43d4a";
        this.runtimeService.suspendProcessInstanceById(processInstanceId);
    }

    /**
     * 激活流程实例
     */
    @Test
    public void activeProcessInstance() {
        String processInstanceId = "354709ac-477f-11ed-abfa-e4a8dfd43d4a";
        this.runtimeService.activateProcessInstanceById(processInstanceId);
    }

    /**
     * 删除流程实例
     */
    @Test
    public void deleteProcessInstance() {
        String processInstanceId = "354709ac-477f-11ed-abfa-e4a8dfd43d4a";
        String reason = "测试删除流程实例";
        this.runtimeService.deleteProcessInstance(processInstanceId, reason);
    }
}