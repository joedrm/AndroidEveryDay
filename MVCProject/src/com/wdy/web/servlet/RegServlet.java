package com.wdy.web.servlet;

import com.wdy.com.wdy.exception.UserExistExcetion;
import com.wdy.domin.User;
import com.wdy.service.UserService;
import com.wdy.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wdy on 16/9/20.
 */
public class RegServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获取表单数据
        User user = new User();
        try {
            ConvertUtils.register(new DateConverter(),Date.class);
            BeanUtils.populate(user, request.getParameterMap());
            // 调用业务逻辑
            UserService userService = new UserServiceImpl();

            userService.findUserByName(user.getUsername());

        userService.register(user);
        } catch (UserExistExcetion e1) {

        }catch (Exception e) {
            e.printStackTrace();
        }

        // 分发转向
        response.getWriter().write("注册成功!1秒钟跳到主页");
        // 跳到主页登录
        response.setHeader("refresh","1;url="+request.getContextPath()+"/index.jsp");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
