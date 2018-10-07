package com.wyh.CloneT.Controller;

import com.wyh.CloneT.AOP.LogAspect;
import com.wyh.CloneT.Service.TouTiaoService;
import com.wyh.CloneT.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.*;
import java.util.*;

/**
 * @author Wu YuHao thecoco
 * @date 2018/9/22 下午5:33
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TouTiaoService touTiaoService;

    @RequestMapping(path = {"/","/index"})
    @ResponseBody
    public String index(HttpSession session){
        logger.info("Visit index:");
        logger.debug("Debug:");
        return "Hello NowCoder,"+session.getAttribute("msg")
                +"<br>Say:"+touTiaoService.say();
    }
@RequestMapping(path = {"/profile/{groupId}/{userId}"})
@ResponseBody
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type",defaultValue = "1") int type,
                          @RequestParam(value = "key",defaultValue = "wyh")int key) {
        return String.format("{%s},{%d},{%d},{%s}",groupId,userId,type,key);
    }

    @RequestMapping(value = {"/vm"})
    public String news(Model model){
        model.addAttribute("value1","vv1");

        //asList()————Returns a fixed-size list backed by the specified array.
        List<String> colors = Arrays.asList(new String[] {"RED","GREEN","BLUE"});

        Map<String,String> map =new HashMap<String, String>();
        for (int i=0;i<4;++i){
            map.put(String.valueOf(i),String.valueOf(i * i));
        }
        model.addAttribute("color",colors);
        model.addAttribute("map",map);
        model.addAttribute("user",new User("Jim"));
        return "news";
    }

    @RequestMapping(value = {"/request"})
    @ResponseBody
    public String request(HttpServletRequest request, HttpServletResponse response,
                          HttpSession session){
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerName = request.getHeaderNames();
        while (headerName.hasMoreElements()){
            String name =headerName.nextElement();
            sb.append(name+":"+request.getHeader(name)+"<br>");
        }
        for (Cookie cookie:request.getCookies()){
            sb.append("Cookid:");
            sb.append(cookie.getName());
            sb.append(":");
            sb.append(cookie.getValue());
            sb.append("<br>");
        }

        sb.append("getMethod"+request.getMethod());

    return sb.toString();
    }

    @RequestMapping(value = {"/response"})
    @ResponseBody
    public String response(@CookieValue(value = "nowcoderid",defaultValue = "a")String nowcoderId,
                           @RequestParam(value = "key",defaultValue = "key")String key,
                           @RequestParam(value = "value",defaultValue = "value")String value,
                           HttpServletResponse response){
        response.addCookie(new Cookie(key,value));
        response.addHeader(key,value);
        return "NowCoder Id from Cookie :"+ nowcoderId;

    }

//    @RequestMapping("/redirect/{code}")
//    public RedirectView redirect(@PathVariable("code") int code){
//        RedirectView red = new RedirectView("/",true);
//        if (code == 301){
//            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//        }
//        return red;
//    }

    @RequestMapping("/redirect/{code}")
    public String redirect(@PathVariable("code") int code,
                           HttpSession session) {
        /*
        RedirectView red = new RedirectView("/", true);
        if (code == 301) {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return red;*/
        session.setAttribute("msg", "Jump from redirect.");
        return "redirect:/";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(@RequestParam(value = "key",required = false)String key){
        if ("admin".equals(key)){
            return "hello admin";
        }
        throw new IllegalArgumentException("Key wrong");
    }

    @ExceptionHandler()
    @ResponseBody
    public String error(Exception e){
        return "error:"+e.getMessage();
    }


}
