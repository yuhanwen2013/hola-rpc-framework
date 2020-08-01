package cn.yubutong.impl;

import cn.yubutong.Hello;
import cn.yubutong.annotation.RpcService;

@RpcService(group = "test1", version = "version2")
public class HelloServiceImpl implements HelloService {
    static {
        System.out.println("实现类创建完成： " + "HelloServiceImpl");
    }

    @Override
    public String hello(Hello hello) {
        return "服务调用成功，内容为： " + hello;
    }
}
