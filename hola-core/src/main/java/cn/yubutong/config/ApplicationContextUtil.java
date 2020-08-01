package cn.yubutong.config;

import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil {
    public static ApplicationContextUtil instance = new ApplicationContextUtil();
    private ApplicationContext _context;
    private ApplicationContextUtil(){}
    public void init(ApplicationContext context){
        _context = context;
    }
    public ApplicationContext getContext(){
        return _context;
    }
    /**
     * 获取Bean
     * @param requiredType
     * @return
     */
    public <T> T getBean(Class<T> requiredType){
        return _context.getBean(requiredType);
    }
}