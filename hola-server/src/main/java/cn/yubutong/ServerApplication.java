package cn.yubutong;

import cn.yubutong.entity.RpcServiceProperties;
import cn.yubutong.impl.HelloService;
import cn.yubutong.impl.HelloServiceImpl;
import cn.yubutong.remoting.transport.netty.server.NettyServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yuhanwen
 */
@ComponentScan("cn.yubutong")
public class ServerApplication {
    public static void main(String[] args) {
        // Register service via annotation
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ServerApplication.class);
        NettyServer nettyServer = applicationContext.getBean(NettyServer.class);
        // Register service manually
        HelloService helloService2 = new HelloServiceImpl();
        RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder()
                .group("test1").version("version2").build();
        nettyServer.registerService(helloService2, rpcServiceProperties);
        nettyServer.start();
    }
}
