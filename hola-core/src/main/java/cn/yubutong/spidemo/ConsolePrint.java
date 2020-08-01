package cn.yubutong.spidemo;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsolePrint implements IPrint {
//    private static final Logger logger = LoggerFactory.getLogger(LogPrint.class);
    @Override
    public void print(String msg) {
        System.out.println(String.format("console print: %s",msg));
//        logger.error("console print: " + msg);
    }

    @Override
    public boolean verify(Integer condition) {
        return condition <= 0;
    }
}