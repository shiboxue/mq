package com.study.mq;

import com.study.mq.dao.UserMapper;
import com.study.mq.services.IThreadTestService;
import com.study.mq.services.IUserService;
import com.study.mq.vo.User;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.study.pachong.SpiderKugou.getTitle;

@RunWith(SpringRunner.class)//就是一个运行器，junit4进行测试
@SpringBootTest//核心测试注解
@MapperScan(basePackages = "com.study.mq.dao")
public class MqApplicationTests {
    public static String LINK = "https://www.kugou.com/yy/rank/home/PAGE-8888.html?from=rank";//酷狗分页目录
    public static List<Map<String,String>> musicUrlList = new ArrayList<>();//测试存放类

    @Resource
    private UserMapper UserMapper;

    @Resource
    private IThreadTestService threadTestService;

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

    @Test
    public void threadTest() {
        for (int i = 0; i < 5000; i++) {
            threadTestService.test(i);
        }
    }

    @Test
    public void kugouTest() throws IOException {
        for(int i = 1 ; i < 23; i++){
            String url = LINK.replace("PAGE", i + "");
            getTitle(url);
        }
//        FileDownload down = new FileDownload();
//
//        for (int i = 0; i <musicUrlList.size() ; i++) {
//            Map<String, String> map = musicUrlList.get(i);
//            System.out.println(map.get("musicName"));
//            down.download(map.get("playUrl"), map.get("musicName"));
//        }
    }


}
