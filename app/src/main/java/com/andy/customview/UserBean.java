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
    private int type;

    public UserBean(String userName, String sex, int type) {
        this.userName = userName;
        this.sex = sex;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
