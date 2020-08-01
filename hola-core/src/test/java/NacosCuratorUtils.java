import cn.yubutong.enumeration.RpcConfigProperties;
import cn.yubutong.utils.file.PropertiesFileUtils;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Properties;

@Slf4j
public class NacosCuratorUtils {

    //nacos默认地址
    private static String defaultNacosAddress = "60.205.207.62";
    private static String defaultNacosPort = "8848";
    //nacos服务状态flag 服务在线UP，下线DOWN
    private static String serviceStatus = "UP";

    private static NamingService namingService = null;


    public static NamingService CuratorUtils() throws NacosException {
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

    public static void registerInstance(String serviceName) throws NacosException {
        namingService.registerInstance(serviceName, defaultNacosAddress, Integer.parseInt(defaultNacosPort));
    }

    @Test
    public void hello() throws NacosException {
        NamingService namingService = NamingFactory.createNamingService(defaultNacosAddress +":"+ defaultNacosPort);
        namingService.registerInstance("nacos.test.test", "11.11.11.11", 8888, "TEST1");
//        namingService.deregisterInstance("nacos.test.3", "11.11.11.11", 8888, "TEST1");
        log.info("nacos status: " + namingService.getServerStatus());
    }
}
