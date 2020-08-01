package cn.yubutong.registry.nacos.util;

import cn.yubutong.enumeration.RpcConfigProperties;
import cn.yubutong.exception.RpcException;
import cn.yubutong.remoting.transport.netty.server.NettyServer;
import cn.yubutong.utils.file.PropertiesFileUtils;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class NacosCuratorUtils {

    //nacos默认地址
    private static String defaultNacosAddress = "127.0.0.1";

    private static String defaultNacosPort = "8848";
    //nacos服务状态flag 服务在线UP，下线DOWN
    private static String serviceStatus = "UP";

    private static final Map<String, List<String>> SERVICE_ADDRESS_MAP = new ConcurrentHashMap<>();

    private static NamingService namingService = null;


    public static NamingService getNamingService() throws NacosException {
        //获取nacos注册地址配置
        Properties properties = PropertiesFileUtils.readPropertiesFile(RpcConfigProperties.RPC_CONFIG_PATH.getPropertyValue());
        if (properties != null) {
            defaultNacosAddress = properties.getProperty(RpcConfigProperties.NACOS_ADDRESS.getPropertyValue());
        }
        if (namingService != null && serviceStatus.equals(namingService.getServerStatus())) {
            return namingService;
        }
        namingService = NamingFactory.createNamingService(defaultNacosAddress);
        return namingService;
    }

    public static void registerInstance(String serviceName, NamingService namingService) throws NacosException, UnknownHostException {
        String host = InetAddress.getLocalHost().getHostAddress();
        namingService.registerInstance(serviceName, host, NettyServer.PORT);
        Instance instance = new Instance();
        instance.setIp(host);
        instance.setPort(NettyServer.PORT);
        instance.setHealthy(true);
        instance.setEnabled(true);
        instance.setServiceName(serviceName);
        namingService.registerInstance(serviceName,instance);
    }

    private static void registerWatcher(String rpcServiceName, NamingService namingService) throws NacosException {

    }

    public static void clearRegistry(NamingService namingService) {

    }

    public static List<String> getChildrenNodes(NamingService namingService, String rpcServiceName) {
        if (SERVICE_ADDRESS_MAP.containsKey(rpcServiceName)) {
            return SERVICE_ADDRESS_MAP.get(rpcServiceName);
        }
        List<String> result;
        try {
            result = namingService.selectInstances(rpcServiceName,true).stream()
                    .map(e -> e.getIp() +":"+ e.getPort()).collect(Collectors.toList());;
            SERVICE_ADDRESS_MAP.put(rpcServiceName, result);
            registerWatcher(rpcServiceName, namingService);
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e.getCause());
        }
        return result;
    }
}
