package com.login;

import com.entity.*;

public class Session { 
    private static Person user;
    
    public static Person getUser() {
        return user;
    } 
    public static void setUser(Person user) {
        Session.user = user; 
    }
}
