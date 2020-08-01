package cn.yubutong.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author yubutong
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface HolaRpcService {
}
