package cn.yubutong.impl;


import cn.yubutong.Hello;
import cn.yubutong.annotation.RpcInterface;

public interface HelloService {
    String hello(Hello hello);
}
