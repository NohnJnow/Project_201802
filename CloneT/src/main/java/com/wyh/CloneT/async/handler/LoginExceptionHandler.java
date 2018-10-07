package com.wyh.CloneT.async.handler;

import com.wyh.CloneT.Service.MessageService;
import com.wyh.CloneT.async.EventHandler;
import com.wyh.CloneT.async.EventModel;
import com.wyh.CloneT.async.EventProducer;
import com.wyh.CloneT.async.EventType;
import com.wyh.CloneT.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wu YuHao thecoco
 * @date 2018/10/5 6:43 PM
 */
@Component
public class LoginExceptionHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    EventProducer eventProducer;


    @Override
    public void doHandle(EventModel model) {
        //判断登陆状态
        Message message = new Message();
        message.setToId();

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LOGIN);
    }
}
