package cn.yubutong.annotation;

import java.lang.annotation.*;

/**
 * @author yubutong
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {
    /**
     * 缺省扩展点名
     * default extension name
     */
    String value() default "";
}