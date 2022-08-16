package com.panda.workflow;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-16  15:50
 * @Description: TODO
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {
    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void deployTest() {
        //添加流程图到数据库
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("test.bpmn")
                .name("测试流程")
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
    }


    @Test
    public void queryTest() {
        //查询返回
        BpmnModel deployment = repositoryService.getBpmnModel("Process_1:1:9cdf1425-1d39-11ed-8e6c-e454e8baf0aa");
        System.out.println("部署ID：");
    }
}