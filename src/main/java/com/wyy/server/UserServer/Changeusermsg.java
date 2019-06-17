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
 * Created by dell on 2019/5/22 0022.
 */

public class Changeusermsg extends HttpServlet {
    UserDaoImp userDaoImp ;

    @Override
    public void init() throws ServletException {
        userDaoImp = UserDaoImp.getInstance();
        super.init();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String uid = request.getParameter("uid");
        String lv = request.getParameter("lv");
        String ass = request.getParameter("ass");
        String goal = request.getParameter("goal");
        JSONObject jsonObject = new JSONObject();
        try{
            userDaoImp.Changemsg(uid,ass,goal,lv);
            jsonObject.put("result",1);
        }catch (Exception e){
            jsonObject.put("result",0);
        }

        out.print(jsonObject);
    }
}
