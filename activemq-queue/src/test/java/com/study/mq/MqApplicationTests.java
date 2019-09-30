package com.study.mq;

import com.study.mq.dao.UserMapper;
import com.study.mq.services.IUserService;
import com.study.mq.vo.User;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)//就是一个运行器，junit4进行测试
@SpringBootTest//核心测试注解
@MapperScan(basePackages = "com.study.mq.dao")
public class MqApplicationTests {
    @Resource
    private UserMapper UserMapper;

    /**
     * 测试mybatis查询结果
     */
    @Test
    public void contextLoads() {
        User user = new User();
        user.setName("zhangsan");
        user.setPassword("1234556");
        UserMapper.insert(user);
        List<User> list = UserMapper.getAll();
        System.out.println(list);
    }

    /**
     * webService测试，通过CXF来实现
     */
    @Test
    public void cxfTest() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:10909/services/user?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(IUserService.class);
        IUserService userService=(IUserService)jaxWsProxyFactoryBean.create();
        List<User> userResult= userService.selectAll();
        System.out.println("userResult:"+userResult);
    }

}
