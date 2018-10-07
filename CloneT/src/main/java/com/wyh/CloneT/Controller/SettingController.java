package com.wyh.CloneT.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Wu YuHao thecoco
 * @date 2018/9/22 下午5:39
 */

@Controller
public class SettingController {
    @RequestMapping("/setting")
    @ResponseBody
    public String setting (){
        return "setting OK";
    }
}
