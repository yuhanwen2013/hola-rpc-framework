package cn.yubutong.config;

import cn.yubutong.registry.zk.util.CuratorUtils;
import cn.yubutong.utils.concurrent.threadpool.ThreadPoolFactoryUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class CustomShutdownHook {

    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();

    public static CustomShutdownHook getCustomShutdownHook(){
        return CUSTOM_SHUTDOWN_HOOK;
    }

    public void clearAll() {

        log.info("addShutdownHook for clearAll");
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            CuratorUtils.clearRegistry(CuratorUtils.getZkClient());
//            ThreadPoolFactoryUtils.shutDownAllThreadPool();
//        }));
    }

}
