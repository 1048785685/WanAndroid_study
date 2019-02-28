package com.example.liuyang05_sx.androidstudy.bean.event;

public class LoginEvent {
    private boolean isLogin;
    private String username;
    public LoginEvent(boolean isLogin,String username) {
        this.isLogin = isLogin;
        this.username = username;
    }
    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
