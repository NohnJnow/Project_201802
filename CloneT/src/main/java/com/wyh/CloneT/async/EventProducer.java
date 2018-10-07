package com.wyh.CloneT.async;

import com.alibaba.fastjson.JSONObject;
import com.wyh.CloneT.Util.JedisAdapter;
import com.wyh.CloneT.Util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wu YuHao thecoco
 * @date 2018/10/5 3:58 PM
 */
@Service
public class EventProducer {
    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireEvent(EventModel model){
        try {
            String json = JSONObject.toJSONString(model);
            String key = RedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(key, json);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
