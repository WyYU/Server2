package com.wyy.server.UserServer;

import com.wyy.dao.UserDaoImp;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2019/3/11 0011.
 */
public class Login extends HttpServlet {
    UserDaoImp userDaoImp ;
    @Override
    public void init() throws ServletException {
        try {
            userDaoImp = UserDaoImp.getInstance();
        } catch (Exception e) {
            System.out.println("Connection to Database Field");
        }
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
        System.out.println("userlogin  user "+username+ "pwd"+password);
        int result = userDaoImp.login(username,password);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        out.write(jsonObject.toString());
    }
    @Override
    public void destroy() {
        if (userDaoImp!=null){
            userDaoImp = null;
        }
    }

}
