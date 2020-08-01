package cn.yubutong.spidemo;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

@Component
public class LogPrint implements IPrint {

    //    private static final Logger logger = LoggerFactory.getLogger(LogPrint.class);
    @Override
    public void print(String msg) {
        System.out.println(String.format("log print: %s", msg));
//        logger.error("log print: {}", msg);
    }

    @Override
    public boolean verify(Integer condition) {
        return condition > 0;
    }
}