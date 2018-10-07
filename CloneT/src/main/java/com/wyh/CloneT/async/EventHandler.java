package com.wyh.CloneT.async;

import java.util.List;

/**
 * @author Wu YuHao thecoco
 * @date 2018/10/5 3:54 PM
 */
public interface EventHandler {
    void doHandle(EventModel model);
    List<EventType> getSupportEventTypes();
}
