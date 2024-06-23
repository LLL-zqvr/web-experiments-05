package com.example.filter;

import com.example.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebFilter("/filter/*")
public class LoginFilter extends HttpFilter {
    private List<String> excludes = List.of("/filter/login");

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //排除路径
        for(String e : excludes) {

            if(e.equals(req.getServletPath())) {
                chain.doFilter(req,res);
                return;
            }
        }
        User user = (User) req.getSession().getAttribute("user");
        if(user != null){
            chain.doFilter(req,res);

        }else {
            //重定向
            //request.getContextPath()获取的是项目名
            res.sendRedirect(req.getContextPath() + "/login");
            //System.out.println(req.getContextPath() + "/login");
        }
    }
}
