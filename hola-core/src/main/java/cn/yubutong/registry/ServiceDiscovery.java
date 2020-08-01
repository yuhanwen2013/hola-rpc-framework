package cn.yubutong.registry;

import com.alibaba.nacos.api.exception.NacosException;

import java.net.InetSocketAddress;


public interface ServiceDiscovery {
    /**
     * lookup service by rpcServiceName
     *
     * @param rpcServiceName rpc service name
     * @return service address
     */
    InetSocketAddress lookupService(String rpcServiceName) throws NacosException;
}
