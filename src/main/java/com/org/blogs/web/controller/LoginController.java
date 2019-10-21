package com.org.blogs.web.controller;

import com.org.blogs.entity.UserInfo;
import com.org.blogs.service.UserService;
import com.org.blogs.util.DateUtil;
import com.org.blogs.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;

/**
 * Created by yxy on 2019/9/10.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    private Color getRandColor(int fc, int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }

    @RequestMapping("admin/checkCode")
    @ResponseBody
    public void checkCode(HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        // 在内存中创建图象
        int width=60, height=24;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        //生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);

        //设定字体
        g.setFont(new Font("Times New Roman",Font.PLAIN,18));

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160,200));
        for (int i=0;i<155;i++)
        {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }

        // 取随机产生的认证码(4位数字)
        String sRand="";
        for (int i=0;i<4;i++){
            String rand=String.valueOf(random.nextInt(10));
            sRand+=rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand,13*i+6,16);
        }

        // 将认证码存入SESSION
        session.setAttribute("checkCode",sRand);

        // 图象生效
        g.dispose();

        // 输出图象到页面
        ImageIO.write(image, "JPEG", response.getOutputStream());
        //下边两行是防止抛出getOutputStream() has already been called for this response异常的
    }

    /**
     * 登录
     */
    @RequestMapping("admin/managerLoginDo")
    public String managerLoginDo(Model model, HttpServletRequest request,
                                 HttpSession session,
                                 HttpServletResponse response) throws Exception{

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordByMd5 = MDUtil.MD5(password);
        String checkCode = request.getParameter("checkCode").trim();
        //验证码
        String sessionCheckCode = session.getAttribute("checkCode").toString();


        String beforePath=(String)request.getSession().getAttribute("beforePath");
        String method=(String)request.getSession().getAttribute("method");
        if(beforePath!=null) {
            if("POST".equals(method)) {
                request.getRequestDispatcher(beforePath).forward(request, response);
            } else {

            }
        }


        if (checkCode.equals(sessionCheckCode)) {
            UserInfo manager = userService.selectByUserNameAndPassWord(username, passwordByMd5);
            if (manager != null && !manager.equals("")) {
                session.setAttribute("manager", manager);
                return "redirect:/";

            } else {
                model.addAttribute("msg", "用户名或密码错误!");
                return "frontPages/login";
            }
        } else {
            model.addAttribute("msg", "验证码错误!");
            return "frontPages/login";
        }
    }

    @SuppressWarnings("all")
    private StringBuffer getRequestParameters(StringBuffer sb, HttpServletRequest req){
        Map map = req.getParameterMap();
        String str = "?";
        if(!map.isEmpty()){
            for(Object key : map.keySet()){
                String[] values = (String[])map.get(key);
                for(String value:values){
                    str+=key+"="+value+"&";
                }
            }
            str = str.substring(0, str.length()-1);
        }
        return sb.append(str);
    }

    //登出
    @RequestMapping("frontPages/logout")
    public String managerLoginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "frontPages/loginOut";
    }

    //登录
    @RequestMapping("frontPages/login")
    public String login(HttpServletRequest request) {
        return "frontPages/login";
    }
    //注册
    @RequestMapping("frontPages/resiger")
    public String resiger(HttpServletRequest request) {
        return "frontPages/resiger";
    }

    //实现注册
    @RequestMapping(value = "admin/resiger", method = RequestMethod.POST)
    @ResponseBody
    public String add(Model model,HttpServletRequest request,HttpSession session) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordByMd5 = MDUtil.MD5(password);
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String createDateTime = DateUtil.getData();
        String checkCode = request.getParameter("checkCode").trim();
        //验证码
        String sessionCheckCode = session.getAttribute("checkCode").toString();
        if (checkCode.equals(sessionCheckCode)) {
            UserInfo user = userService.selectUserNameForLogin(username);
            if (user != null) {
                model.addAttribute("msg", "用户账号已经被注册");
                return "frontPages/login";
            }else {
                UserInfo users = new UserInfo();
                users.setEmail(email);
                users.setCreateDateTime(createDateTime);
                users.setPassword(passwordByMd5);
                users.setStatus(1);
                users.setUserName(username);
                users.setName(name);
                if(userService.insertUser(users)>0){
                    return "redirect:/frontPages/login";
                }
            }
        }else {
            model.addAttribute("msg", "验证码输入错误！");
            return "frontPages/resiger";
        }
        return "frontPages/resiger";
    }
}
