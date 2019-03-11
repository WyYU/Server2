package com.wyy.server;

import com.wyy.dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2019/3/11 0011.
 */
public class Login extends HttpServlet {
    UserDaoImp userDaoImp ;
    @Override
    public void init() throws ServletException {
        userDaoImp = new UserDaoImp();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("pwd").trim();
        int result = userDaoImp.login(username,password);
        Map<String, String> params = new HashMap<>();

        out.write("<h1>" +result+"</h1>");
    }

}
