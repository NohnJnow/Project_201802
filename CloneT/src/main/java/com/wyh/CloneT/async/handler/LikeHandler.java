package com.wyh.CloneT.async.handler;

import com.wyh.CloneT.Service.MessageService;
import com.wyh.CloneT.Service.UserService;
import com.wyh.CloneT.async.EventHandler;
import com.wyh.CloneT.async.EventModel;
import com.wyh.CloneT.async.EventType;
import com.wyh.CloneT.model.Message;
import com.wyh.CloneT.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Wu YuHao thecoco
 * @date 2018/10/5 3:56 PM
 */
@Component
public class LikeHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        Message message  = new Message();
        message.setFromId(3);
        message.setToId(model.getEntityOwnerId());
        User user = userService.getUser(model.getActorId());
        message.setContent("用户"+user.getName()+"zan le zi xun"+model.getEntityId());
        message.setCreatedDate(new Date());
        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
