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

@WebServlet({"/welcome"})
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String username = null;
        String password = null;
        String relName = null;
        if(cookies != null){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)){
                    username = cookie.getValue();
                }
                if ("password".equals(name)){
                    password = cookie.getValue();
                }
            }
        } else {
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }

        if(username != null && password != null){
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = DBUtil.getConnection();
                String sql = "select 登录名,登录密码,真实姓名 from user_login where 登录名=? and 登录密码=? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2,password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    relName= rs.getString("真实姓名");
                    HttpSession session = req.getSession();
                    User user = new User(username,password);
                    session.setAttribute("user",user);
                    session.setAttribute("name",relName);
                    resp.sendRedirect(req.getContextPath()+"/dept/list");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }
        } else {
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }


    }
}
