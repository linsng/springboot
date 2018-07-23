package com.lsg.web;

/**
 * @author lsg
 * @version 1.0
 * @date 2018/7/20
 * @since 1.0
 */

public class LoginCommand {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
