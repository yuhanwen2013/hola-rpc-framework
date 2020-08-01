import cn.yubutong.spidemo.IPrint;
import cn.yubutong.spidemo.PrintAutoConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test01 {

    @Test
    public void testSPI() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PrintAutoConfig.class);
        IPrint printProxy = context.getBean("printProxy",IPrint.class);
        printProxy.execute(10, " log print ");
        printProxy.execute(0, " console print ");
    }
}
