package com.controller;
import com.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/filter/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //转发
        req.getRequestDispatcher("/WEB-INF/jsp/login.html")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        String url;
        if("9376".equals(account) && "12345".equals(password)){
            User u = new User("liu");
            req.getSession().setAttribute("user",u);
            url = "/filter/welcome";
        }else {
            url = "/filter/login";
        }
        resp.sendRedirect(req.getContextPath()+url);
    }
}
