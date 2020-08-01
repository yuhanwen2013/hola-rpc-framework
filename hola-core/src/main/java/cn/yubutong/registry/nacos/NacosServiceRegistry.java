package cn.yubutong.registry.nacos;

import cn.yubutong.registry.ServiceRegistry;
import cn.yubutong.registry.nacos.util.NacosCuratorUtils;
import cn.yubutong.registry.zk.util.CuratorUtils;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Slf4j
public class NacosServiceRegistry implements ServiceRegistry {

    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) throws NacosException, UnknownHostException {
        NacosCuratorUtils.registerInstance(rpcServiceName, NacosCuratorUtils.getNamingService());    }
}
