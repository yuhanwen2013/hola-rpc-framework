package cn.yubutong.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;


@Service
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // TODO Auto-generated method stub
        if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
            System.out.println("避免spring-servlet.xml跟appicationContext.xml两次加载");
        }
        if(event.getApplicationContext().getParent()==null){
            System.out.println("执行context上下文初始化");
            ApplicationContextUtil.instance.init(event.getApplicationContext());
        }
    }
}