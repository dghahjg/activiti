package com.example.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
public class ProcessDefinitionTest {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 查询流程定义列表
     */
    @Test
    public void listProcessDefinitions() {
        List<ProcessDefinition> processDefinitions = this.repositoryService.createProcessDefinitionQuery()
                .list();
        if (!CollectionUtils.isEmpty(processDefinitions)) {
            processDefinitions.forEach(processDefinition -> {
                System.out.println("Name：" + processDefinition.getName());
                System.out.println("Key：" + processDefinition.getKey());
                System.out.println("ResourceName：" + processDefinition.getResourceName());
                System.out.println("DeploymentId：" + processDefinition.getDeploymentId());
                System.out.println("Version：" + processDefinition.getVersion());
            });
        }
    }
}