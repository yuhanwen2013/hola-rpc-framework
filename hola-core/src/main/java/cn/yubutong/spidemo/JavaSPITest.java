package cn.yubutong.spidemo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ServiceLoader;

public class JavaSPITest {

   @Autowired
   private IPrint iPrint;
  
    public void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }

    public void main(String[] args) throws Exception {
        new JavaSPITest().logPrint(iPrint);

    }

    public void logPrint(IPrint printProxy){
        printProxy.execute(10, " log print ");
        printProxy.execute(0, " console print ");
    }

}