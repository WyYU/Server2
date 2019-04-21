package com.wyy.server.NotificationSever;

import com.wyy.dao.NotificationDaoImp;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2019/4/21 0021.
 */
public class QueryteamNotiNum extends HttpServlet {
    private NotificationDaoImp notificationDaoImp = new NotificationDaoImp();

    @Override
    public void init() throws ServletException {
        //notificationDaoImp = NotificationDaoImp.getNotificationDaoImp();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json");
        String  tid = request.getParameter("tid");
        String  lastid = request.getParameter("lid");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject =new JSONObject();
        try {
            int number = notificationDaoImp.queryNotinum(Integer.parseInt(tid),Integer.parseInt(lastid));
            jsonObject.put("code","1");
            jsonObject.put("num",number);
        } catch (Exception e){
            jsonObject.put("code","0");
            jsonObject.put("num",0);
        }
        writer.print(jsonObject );
    }
}
