package com.javaweb.bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();
        Object onlineCount = application.getAttribute("onlineCount");
        if (onlineCount == null){
            application.setAttribute("onlineCount",1);
        } else {
            int count = (Integer) application.getAttribute("onlineCount");
            count++;
            application.setAttribute("onlineCount",count);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();
        int count = (Integer) application.getAttribute("onlineCount");
        count--;
        application.setAttribute("onlineCount",count);
    }

    private String username;
    private String password;

    private  String relName;

    public User() {
    }

    public User(String username, String password, String relName) {
        this.username = username;
        this.password = password;
        this.relName = relName;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }
}
