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
    private static final String BASE = "/WEB-INF/jsp/";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //RequestDispatcher。请求转发,将客户端的请求发送至服务器内任何其他资源(servlet/HTML/JSP)
        System.out.println("5");
        req.getRequestDispatcher(BASE + "login.html")//创建一个封装了指定资源的RequestDispatcher对象
                .forward(req,resp);//传递request/response对象
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        System.out.println("6");
        String url;
        if("9376".equals(account) && "12345".equals(password)){
            User u = new User("liu");
            req.getSession().setAttribute("user",u);
            url = "/filter/welcome";
        }else {
            url = "/filter/login";
        }
        System.out.println("7");
        resp.sendRedirect(req.getContextPath()+url);
        //System.out.println(req.getContextPath()+url);
    }
}
