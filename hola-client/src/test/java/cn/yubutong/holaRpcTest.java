package cn.yubutong;

import cn.yubutong.annotation.HolaRpcService;
import cn.yubutong.config.RpcConfig;
import cn.yubutong.impl.HelloService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class holaRpcTest {

//    @Autowired
//    ApplicationContext context;

    @Test
    public void name() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RpcConfig.class);
        HelloService helloService = (HelloService) context.getBean("HelloService");
        helloService.hello(new Hello("test","test"));
    }
}
