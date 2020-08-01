package cn.yubutong.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author yubutong
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Component
public @interface RpcService {

    String version() default "";

    String group() default "";
}
