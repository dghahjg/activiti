package com.example.activiti;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TaskTest {

    @Autowired
    private TaskService taskService;

    /**
     * 查询任务列表
     */
    @Test
    public void listTasks() {
        List<Task> taskList = this.taskService.createTaskQuery().list();
        if (!CollectionUtils.isEmpty(taskList)) {
            taskList.forEach(task -> {
                System.out.println("Id：" + task.getId());
                System.out.println("Name：" + task.getName());
                System.out.println("Assignee：" + task.getAssignee());
            });
        }
    }

    /**
     * 查询我的代办任务
     */
    @Test
    public void listTasksByAssignee() {
        String assignee = "admin";
        List<Task> taskList = this.taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
        if (!CollectionUtils.isEmpty(taskList)) {
            taskList.forEach(task -> {
                System.out.println("Id：" + task.getId());
                System.out.println("Name：" + task.getName());
                System.out.println("Assignee：" + task.getAssignee());
            });
        }
    }

    /**
     * 完成任务
     */
    @Test
    public void completeTask() {
        String taskId = "354b9d90-477f-11ed-abfa-e4a8dfd43d4a";
        Map<String, Object> variables = new HashMap<>(16);
        this.taskService.complete(taskId, variables);
    }

    /**
     * 拾取任务
     */
    @Test
    public void claimTask() {
        String taskId = "16beabc1-479f-11ed-9c3a-e4a8dfd43d4a";
        String userId = "jason";
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        taskService.claim(taskId, userId);
    }

    /**
     * 归还任务
     */
    @Test
    public void returnTask() {
        String taskId = "16beabc1-479f-11ed-9c3a-e4a8dfd43d4a";
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        // 归还任务
        taskService.unclaim(taskId);
    }

    /**
     * 交办任务
     */
    @Test
    public void handoverTask() {
        String taskId = "16beabc1-479f-11ed-9c3a-e4a8dfd43d4a";
        String userId = "jack";
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        // 交办任务
        taskService.setAssignee(taskId, userId);
    }
}