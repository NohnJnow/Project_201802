package com.wyh.CloneT.interceptor;

import com.wyh.CloneT.Dao.LoginTicketDAO;
import com.wyh.CloneT.Dao.UserDAO;
import com.wyh.CloneT.model.HostHolder;
import com.wyh.CloneT.model.LoginTicket;
import com.wyh.CloneT.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Wu YuHao thecoco
 * @date 2018/9/30 3:18 PM
 */
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
      String ticket =null;
      if(httpServletRequest.getCookies() != null)
          for (Cookie cookie:httpServletRequest.getCookies())
              if(cookie.getName().equals("ticket")){
                  ticket = cookie.getValue();
                  break;
              }
        if (ticket != null) {
            LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
                return true;
            }

            User user = userDAO.selectById(loginTicket.getUserId());
            hostHolder.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null && hostHolder.getUser() != null)
            modelAndView.addObject("user",hostHolder.getUser());

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
            hostHolder.clear();
    }
}
