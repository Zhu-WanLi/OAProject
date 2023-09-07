package com.javaweb;

import com.javaweb.bean.User;
import com.javaweb.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/dept/login","/dept/exit"})
public class UserLogin extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("/dept/login".equals(req.getServletPath()))    doLogin(req,resp);
        else if("/dept/exit".equals(req.getServletPath()))     doExit(req,resp);
    }

    private void doExit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if(session!=null)  session.invalidate();  //手动销毁session
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if("username".equals(cookie.getName()) || "password".equals(cookie.getName())){
                    cookie.setMaxAge(0);
                    cookie.setPath(req.getContextPath());  //删除时注意路径
                    resp.addCookie(cookie);
                }
            }
        }

        resp.sendRedirect(req.getContextPath());
    }

    protected void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean success = false;

        try {
            conn = DBUtil.getConnection();
            String sql = "select 登录名,登录密码,真实姓名 from user_login where 登录名=? and 登录密码=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()) {
                success = true;
                String name= rs.getString("真实姓名");
                session.setAttribute("name",name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        if (success){
            User user = new User(username,password);
            session.setAttribute("user",user);

            if (req.getParameter("f") != null && req.getParameter("f").equals("1")) {
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);

                cookie1.setPath(req.getContextPath());
                cookie2.setPath(req.getContextPath());

                cookie2.setMaxAge(60*60);
                cookie1.setMaxAge(60*60);

                resp.addCookie(cookie1);
                resp.addCookie(cookie2);
            }

            resp.sendRedirect(req.getContextPath() + "/dept/list");

        } else {
            resp.sendRedirect(req.getContextPath()+"/passwordError.html");
        }



    }
}
