package cn.yubutong;

import cn.yubutong.config.RpcConfig;
import cn.yubutong.impl.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(RpcConfig.class);
        HelloService helloService = (HelloService) context.getBean("HelloService");
        for (int i = 0; i < 1; i++) {
            helloService.hello(new Hello("这是消息文本", "这是备注文本"));
        }
    }
}
