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
 * Created by dell on 2019/3/12 0012.
 */
public class Changelv extends HttpServlet {
    UserDaoImp userDaoImp;

    @Override
    public void init() throws ServletException {
        userDaoImp = new UserDaoImp();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uid = request.getParameter("uid");
        String lv = request.getParameter("lv");
        JSONObject jsonObject = new JSONObject();
        PrintWriter out = response.getWriter();
        try {
            userDaoImp.levelChange(Integer.parseInt(uid),Integer.parseInt(lv));
        }
        catch (Exception e){
            jsonObject.put("result",0);
        } finally {
            out.print(jsonObject);
        }
        jsonObject.put("result",1);
        out.print(jsonObject);
    }

    @Override
    public void destroy() {
        if (userDaoImp!=null){
            userDaoImp = null;
        }
        super.destroy();
    }
}
