package com.sni.web.annotation;

import java.lang.annotation.*;

/**
 * 操作日志配置类
 * @author Gaoyang
 * @date 2021/2/7 15:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    /**
     * 操作模块
     * @return
     */
    String operationModule() default "";

    /**
     * 操作类型
     * @return
     */
    String operationType() default "";

    /**
     * 操作描述
     * @return
     */
    String operationDesc() default "";
}
