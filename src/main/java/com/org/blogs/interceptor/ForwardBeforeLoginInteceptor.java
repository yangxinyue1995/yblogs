package com.org.blogs.interceptor;

import com.org.blogs.entity.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yxy on 2019/9/12.
 */
public class ForwardBeforeLoginInteceptor implements HandlerInterceptor {

            @Override
            public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1,
                                        Object arg2, Exception arg3) throws Exception {

            }
            @Override
            public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                                   Object arg2, ModelAndView arg3) throws Exception {

            }
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {

        UserInfo user = (UserInfo) request.getSession().getAttribute("manager");
        if(user == null){//说明用户当前没有登录，所以需要记录用户在未登录前访问的页面地址，然后等用户登录以后再跳回之前的页面
            HttpSession session = request.getSession(true);
            String uri = request.getRequestURI();//拿到上一个页面地址
            String path = uri.substring(request.getContextPath().length());//去掉项目地址长度的字符（因为我的默认项目地址是给出的）
            String query = request.getQueryString();//得到参数
            if(query == null) {
                query="";
            }
            else {
                query="?"+query;
            }
            String beforePath = path+query;
            session.setAttribute("beforePath", beforePath);
            session.setAttribute("method", request.getMethod());
        }
        return true;
    }
}
