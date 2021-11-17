package com.jt.demo.aop;

import org.w3c.dom.Entity;

public enum EntityType {
    PRODUCT("product"),APP_USER("user");

    private String presentName;

    EntityType(String presentName){
        this.presentName=presentName;
    }

    @Override
    public String toString (){
        return presentName;
    }

}
