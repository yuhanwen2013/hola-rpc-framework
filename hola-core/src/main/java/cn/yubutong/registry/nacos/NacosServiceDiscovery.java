package cn.yubutong.registry.nacos;

import cn.yubutong.enumeration.RpcErrorMessage;
import cn.yubutong.exception.RpcException;
import cn.yubutong.loadbalance.LoadBalance;
import cn.yubutong.loadbalance.RandomLoadBalance;
import cn.yubutong.registry.ServiceDiscovery;
import cn.yubutong.registry.nacos.util.NacosCuratorUtils;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;

@Slf4j
public class NacosServiceDiscovery implements ServiceDiscovery {
    private final LoadBalance loadBalance;

    public NacosServiceDiscovery() {
        this.loadBalance = new RandomLoadBalance();
    }

    @Override
    public InetSocketAddress lookupService(String rpcServiceName) {
        NamingService namingService = null;
        try {
            namingService = NacosCuratorUtils.getNamingService();
        } catch (NacosException exception) {
            log.warn("NacosException" + exception.getMessage());
        }
        List<String> serviceUrlList = NacosCuratorUtils.getChildrenNodes(namingService, rpcServiceName);
        if (serviceUrlList.size() == 0) {
            throw new RpcException(RpcErrorMessage.SERVICE_CAN_NOT_BE_FOUND, rpcServiceName);
        }
        String targetServiceUrl = loadBalance.selectServiceAddress(serviceUrlList);
        log.info("Successfully found the service address:[{}]", targetServiceUrl);
        String[] socketAddressArray = targetServiceUrl.split(":");
        String host = socketAddressArray[0];
        int port = Integer.parseInt(socketAddressArray[1]);
        return new InetSocketAddress(host, port);

    }
}
