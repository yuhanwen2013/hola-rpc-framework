package cn.yubutong.config;

import cn.yubutong.annotation.HolaRpcService;
import cn.yubutong.annotation.RpcInterface;
import cn.yubutong.annotation.RpcService;
import cn.yubutong.entity.RpcServiceProperties;
import cn.yubutong.proxy.RpcClientProxy;
import cn.yubutong.remoting.transport.ClientTransport;
import cn.yubutong.remoting.transport.netty.client.NettyClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author yubutong
 */
@Configuration
@Slf4j
public class RpcConfig implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Reflections reflections = new Reflections("cn.yubutong");
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(RpcInterface.class);
        Set<Field> ids = reflections.getFieldsAnnotatedWith(HolaRpcService.class);
        for (Class<?> aClass : typesAnnotatedWith) {
            if (aClass.isAnnotationPresent(RpcInterface.class)) {
                RpcInterface rpcService = aClass.getAnnotation(RpcInterface.class);
                ClientTransport rpcClient = new NettyClientTransport();
                RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder()
                        .group(rpcService.group()).version(rpcService.version()).build();
                RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient, rpcServiceProperties);
                Object object = rpcClientProxy.getProxy(aClass);
                System.out.println(aClass.getSimpleName());
                beanFactory.registerSingleton(aClass.getSimpleName(), object);
            }
        }
        log.info("afterPropertiesSet is {}", typesAnnotatedWith);
    }
}
