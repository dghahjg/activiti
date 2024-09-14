package com.example.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
public class HistoricTest {

    @Autowired
    private HistoryService historyService;

    /**
     * 根据用户名查询历史记录
     */
    @Test
    public void listHistoricTasksByAssignee() {
        String assignee = "admin";
        List<HistoricTaskInstance> historicTasks = this.historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime()
                .asc()
                .taskAssignee(assignee)
                .list();
        if (!CollectionUtils.isEmpty(historicTasks)) {
            historicTasks.forEach(historicTaskInstance -> {
                System.out.println("Id：" + historicTaskInstance.getId());
                System.out.println("ProcessInstanceId：" + historicTaskInstance.getProcessInstanceId());
                System.out.println("Name：" + historicTaskInstance.getName());
            });
        }
    }

    /**
     * 根据流程实例ID查询历史
     */
    @Test
    public void listHistoricTasksByProcessInstanceId() {
        String processInstanceId = "0f8a9b00-479e-11ed-af85-e4a8dfd43d4a";
        List<HistoricTaskInstance> historicTasks = this.historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime()
                .asc()
                .processInstanceId(processInstanceId)
                .list();
        if (!CollectionUtils.isEmpty(historicTasks)) {
            historicTasks.forEach(historicTaskInstance -> {
                System.out.println("Id：" + historicTaskInstance.getId());
                System.out.println("ProcessInstanceId：" + historicTaskInstance.getProcessInstanceId());
                System.out.println("Name：" + historicTaskInstance.getName());
            });
        }
    }
}