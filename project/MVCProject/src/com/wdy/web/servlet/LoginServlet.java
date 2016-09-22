package com.wdy.web.servlet;

import com.wdy.domin.User;
import com.wdy.service.UserService;
import com.wdy.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wdy on 16/9/22.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        User user = new User();

        try {
            BeanUtils.populate(user, request.getParameterMap());
            UserService userService = new UserServiceImpl();
            User u = userService.login(user);

            // 分发转向
            if (u!=null){ // 数据库存在该用户
                request.getSession().setAttribute("u", user);
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }else {
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
