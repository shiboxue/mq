package com.study.mq.config;

import com.study.mq.services.IUserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
 public class CxfConfig {
     @Autowired
     private Bus bus;

     @Autowired
     private IUserService userService;

     /**
      * 此方法作用是改变项目中服务名的前缀名，此处127.0.0.1或者localhost不能访问时，请使用ipconfig查看本机ip来访问
      * 此方法被注释后:wsdl访问地址为http://127.0.0.1:8080/services/user?wsdl
      * 去掉注释后：wsdl访问地址为：http://127.0.0.1:8080/soap/user?wsdl
      * @return
      */
//     @Bean
//     public ServletRegistrationBean dispatcherServlet() {
//          return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
//     }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint=new EndpointImpl(bus, userService);//绑定要发布的服务
        endpoint.publish("/user"); //显示要发布的名称
        return endpoint;
    }
}