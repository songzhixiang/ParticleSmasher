package com.andy.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)//该注解作用在属性之上
@Retention(RetentionPolicy.CLASS)//编译期.class是存在于apk中的
public @interface BindView {

    int value();

}
