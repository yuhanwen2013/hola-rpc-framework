package cn.yubutong.registry;

import com.alibaba.nacos.api.exception.NacosException;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;


public interface ServiceRegistry {
    /**
     * register service
     *
     * @param rpcServiceName    rpc service name
     * @param inetSocketAddress service address
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) throws NacosException, UnknownHostException;

}
