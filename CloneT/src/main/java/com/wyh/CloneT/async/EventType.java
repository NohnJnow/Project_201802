package com.wyh.CloneT.async;

/**
 * @author Wu YuHao thecoco
 * @date 2018/10/5 3:44 PM
 */
public enum EventType {
    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3);

    private int value;
    EventType(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
}
