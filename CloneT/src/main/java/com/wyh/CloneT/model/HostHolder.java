package com.wyh.CloneT.model;

import org.springframework.stereotype.Component;

/**
 * @author Wu YuHao thecoco
 * @date 2018/9/30 3:32 PM
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();


    public User getUser(){
        return users.get();
    }

    public void setUser(User user){
        users.set(user);
    }


    public void clear(){
        users.remove();
    }
}
