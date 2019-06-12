package com.andy.customview;

import java.io.Serializable;

/**
 * @author andysong
 * @data 2019-06-12
 * @discription xxx
 */
public class UserBean implements Serializable {

    private String userName;
    private String sex;
    private int age;

    public UserBean(String userName, String sex, int age) {
        this.userName = userName;
        this.sex = sex;
        this.age = age;
    }

    public UserBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
