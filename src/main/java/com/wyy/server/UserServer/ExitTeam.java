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
public class ExitTeam extends HttpServlet {
    UserDaoImp userDaoImp ;

    @Override
    public void init() throws ServletException {
        userDaoImp = new UserDaoImp();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        userDaoImp.deluser(Integer.parseInt(uid));
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        try {
            userDaoImp.exitTeam(Integer.parseInt(uid));
        } catch (Exception e) {
            jsonObject.put("result",0);
        } finally {
            out.print(jsonObject);
        }
        jsonObject.put("result",1);
    }

    @Override
    public void destroy() {
        if(userDaoImp!= null){
            userDaoImp = null;
        }
        super.destroy();
    }
}
