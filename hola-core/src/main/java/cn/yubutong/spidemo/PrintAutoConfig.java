package cn.yubutong.spidemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class PrintAutoConfig {

    @Bean
    public SpiFactoryBean printSpiPoxy(ApplicationContext applicationContext) {
        return new SpiFactoryBean(applicationContext, IPrint.class);
    }

    @Bean
    @Primary
    public <T> T printProxy(SpiFactoryBean spiFactoryBean) throws Exception {
        return (T) spiFactoryBean.getObject();
    }
}