package com.javaweb.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse ;
        String servletPath = req.getServletPath();
        HttpSession session = req.getSession(false);
        if ("/dept/login".equals(req.getServletPath()) ||  "/dept/exit".equals(req.getServletPath()) ||
                "/welcome".equals(req.getServletPath()) || "/index.jsp".equals(req.getServletPath()) ||
                "/passwordError.html".equals(servletPath) || (session!=null && session.getAttribute("user")!=null)) {
            filterChain.doFilter(req,resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
}
