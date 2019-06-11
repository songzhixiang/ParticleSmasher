package com.andy.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//该注解作用在方法之上
@Retention(RetentionPolicy.CLASS)//编译期.class是存在于apk中的
public @interface onClick {

    int value();

}
