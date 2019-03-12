package com.wyy.server;

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
public class Regist extends HttpServlet {
    UserDaoImp userDaoImp;

    @Override
    public void init() throws ServletException {
        userDaoImp = new UserDaoImp();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       super.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("pwd");
        int i = userDaoImp.regiest(username,password);
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",i);
        out.print(jsonObject.toString());
    }

    @Override
    public void destroy() {
        if (userDaoImp!=null){
            userDaoImp = null;
        }
    }
}
