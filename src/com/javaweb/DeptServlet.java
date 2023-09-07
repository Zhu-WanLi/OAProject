package com.javaweb;

import com.javaweb.bean.Dept;
import com.javaweb.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/dept/list","/dept/add","/dept/delete","/dept/detail","/dept/edit","/dept/modify"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String servletPath = req.getServletPath();
            if ("/dept/list".equals(servletPath)) doList(req, resp);

            if ("/dept/add".equals(servletPath)) doAdd(req, resp);

            if ("/dept/delete".equals(servletPath)) doDel(req, resp);

            if ("/dept/detail".equals(servletPath)) doDetail(req, resp);

            if ("/dept/edit".equals(servletPath)) doEdit(req, resp);

            if ("/dept/modify".equals(servletPath)) doModify(req, resp);

    }

    private void doList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String contextPath = req.getContextPath(); //获取应用根路径

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs = null;
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        List<Dept> deptList = new ArrayList<>();

        try {
            conn= DBUtil.getConnection();
            String sql="select deptno,dname,loc from dept";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();

            int i=0;
            while (rs.next()){
                String deptno=rs.getString("deptno");
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");
                Dept depts = new Dept(deptno,dname,loc);
                deptList.add(depts);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        req.setAttribute("deptList",deptList);
        req.getRequestDispatcher("/list.jsp").forward(req,resp);

    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String deptno = req.getParameter("deptno");
        String dname = req.getParameter("dname");
        String loc = req.getParameter("loc");

        Connection conn=null;
        PreparedStatement ps=null;
        int count=0;

        try {
            conn= DBUtil.getConnection();
            String sql="insert into dept(deptno,dname,loc) values(?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }

        if (count==1) {
            resp.sendRedirect(req.getContextPath()+"/dept/list");
        } else {
            resp.sendRedirect(req.getContextPath()+"/error.html");
        }
    }
    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptno = req.getParameter("deptno");
        Connection conn=null;
        PreparedStatement ps=null;
        int count=0;
        try {
            conn= DBUtil.getConnection();
            conn.setAutoCommit(false);
            String sql="delete from dept where deptno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,deptno);
            count = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if(conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
        if (count==1) {
            req.getRequestDispatcher("/dept/list").forward(req,resp);
        }else {
            req.getRequestDispatcher("/error.html").forward(req,resp);
        }
    }

    private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String deptno = req.getParameter("deptno");
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs = null;

        try {
            conn= DBUtil.getConnection();
            String sql="select deptno,dname,loc from dept where deptno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs=ps.executeQuery();
            if(rs.next()){
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");
                Dept dept = new Dept(deptno,dname,loc);
                req.setAttribute("dept",dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        req.getRequestDispatcher("/detail.jsp").forward(req,resp);
    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String contextPath = req.getContextPath();
        String deptno = req.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            conn= DBUtil.getConnection();
            String sql="select deptno,dname,loc from dept where deptno=?";
            ps= conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs= ps.executeQuery();
            if(rs.next()){
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept = new Dept(deptno,dname,loc);
                req.setAttribute("dept",dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        req.getRequestDispatcher("/edit.jsp").forward(req,resp);

    }

    private void doModify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String deptno = req.getParameter("deptno");
        String dname = req.getParameter("dname");
        String loc = req.getParameter("loc");
        Connection conn=null;
        PreparedStatement ps=null;
        int count=0;
        try {
            conn= DBUtil.getConnection();
            String sql="update dept set dname=?,loc=? where deptno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setString(3,deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
        if (count==1) {
            resp.sendRedirect(req.getContextPath()+"/dept/list");
        } else {
            req.getRequestDispatcher("/error.html").forward(req,resp);
        }
    }
}
