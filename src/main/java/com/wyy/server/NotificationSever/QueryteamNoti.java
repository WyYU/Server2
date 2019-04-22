package com.wyy.server.NotificationSever;

import com.wyy.dao.NotificationDaoImp;
import com.wyy.po.Notification;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by dell on 2019/3/25 0025.
 */
public class QueryteamNoti extends HttpServlet {
   NotificationDaoImp notificationDaoImp;

    @Override
    public void init() throws ServletException {
        notificationDaoImp = NotificationDaoImp.getNotificationDaoImp();
        super.init();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json");
        String tid = request.getParameter("tid");
        PrintWriter out = response.getWriter();;
        List<Notification> list = notificationDaoImp.queryNoti(Integer.parseInt(tid));
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i<list.size();i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Data",list.get(i).getData().toString());
            jsonObject.put("Context",list.get(i).getContext());
            jsonObject.put("nid",list.get(i).getId());
            System.out.println(jsonObject);
            jsonArray.add(jsonObject);
        }
        out.print(jsonArray);
    }
}
