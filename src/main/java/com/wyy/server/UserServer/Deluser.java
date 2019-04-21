package com.wyy.server.UserServer;

import com.wyy.dao.NotificationDaoImp;
import com.wyy.dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2019/3/11 0011.
 */
public class Deluser extends HttpServlet {
    UserDaoImp userDaoImp ;
    NotificationDaoImp notation;

    @Override
    public void init() throws ServletException {
        userDaoImp = UserDaoImp.getInstance();
        notation = NotificationDaoImp.getNotificationDaoImp();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        userDaoImp.deluser(Integer.parseInt(uid));
        PrintWriter out = response.getWriter();
        out.print("Delete success");

    }

    @Override
    public void destroy() {
        if(userDaoImp!= null){
            userDaoImp = null;
        }
        super.destroy();
    }
}
