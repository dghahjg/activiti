package com.example.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@SpringBootTest
public class DeploymentTest {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 流程部署
     */
    @Test
    public void initDeployment() {
        String fileName = "bpmn/Part1_Deployment.bpmn20.xml";
        Deployment deployment = this.repositoryService.createDeployment()
                .addClasspathResource(fileName)
                .name("流程部署测试")
                .deploy();
        System.out.println("流程部署名称：" + deployment.getName());
    }

    /**
     * 流程部署（Zip包）
     */
    @Test
    public void initDeploymentByZip() {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("bpmn/Part1_Deployment.zip");
        assert inputStream != null;
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deployment = this.repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("流程部署测试（Zip包）")
                .deploy();
        System.out.println("流部署名称：" + deployment.getName());
    }

    /**
     * 查询流程部署列表
     */
    @Test
    public void listDeployments() {
        List<Deployment> deployments = this.repositoryService.createDeploymentQuery().list();
        if (!deployments.isEmpty()) {
            deployments.forEach(deployment -> {
                System.out.println("Id：" + deployment.getId());
                System.out.println("Name：" + deployment.getName());
                System.out.println("DeploymentTime：" + deployment.getDeploymentTime());
                System.out.println("Key：" + deployment.getKey());
            });
        }
    }

    /**
     * 删除对流程实例、历史流程实例和作业的给定部署和级联删除
     */
    @Test
    public void deleteDeployment() {
        String deploymentId = "4c97f9ce-4774-11ed-930a-e4a8dfd43d4a";
        this.repositoryService.deleteDeployment(deploymentId, false);
    }
}