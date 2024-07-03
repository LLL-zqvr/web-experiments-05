package com.filter;
import com.entity.User;
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
        System.out.println("1");
        for(String e : excludes) {

            //如果相等，既是登录页面的话直接就放过去
            if(e.equals(req.getServletPath())) {
                //System.out.println(e +" " + req.getServletPath());
                System.out.println("2");
                chain.doFilter(req,res);
                return;
            }
        }

        User user = (User) req.getSession().getAttribute("user");
        if(user != null){
            chain.doFilter(req,res);
        }
            //重定向
            //request.getContextPath()获取的是项目名
        //System.out.println("3");
            res.sendRedirect(req.getContextPath() + "/filter/login");
//        System.out.println();
            //System.out.println(req.getContextPath());
        //System.out.println(req.getServletPath());

    }
}
