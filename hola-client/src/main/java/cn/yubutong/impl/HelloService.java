package cn.yubutong.impl;


import cn.yubutong.Hello;
import cn.yubutong.annotation.HolaRpcService;
import cn.yubutong.annotation.RpcInterface;

@RpcInterface(group = "test1", version = "version2")
public interface HelloService {

    String hello(Hello hello);

}
