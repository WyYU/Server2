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
 * Created by dell on 2019/3/25 0025.
 */
public class CreatenewNoti extends HttpServlet {
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
        PrintWriter out = response.getWriter();
        String tid = request.getParameter("tid");
        String context = request.getParameter("context");
        JSONObject res = new JSONObject();
        try{
            notificationDaoImp.createNoti(Integer.parseInt(tid),context);
        }catch (Exception e) {
            res.put("result","0");
            out.print(res);
            return;
        }
        res.put("result","1");
        out.print(res);
        return;
    }
}
