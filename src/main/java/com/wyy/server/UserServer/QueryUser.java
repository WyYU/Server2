package com.wyy.server.UserServer;

import com.wyy.dao.UserDaoImp;
import com.wyy.po.User;
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
public class QueryUser extends HttpServlet {
    UserDaoImp userDaoImp ;

    @Override
    public void init() throws ServletException {
        userDaoImp = new UserDaoImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String value = req.getParameter("value");
        String key = req.getParameter("key");
        User user;
        JSONObject jsonObject = new JSONObject();
        if (key.equals("id")){
            user = userDaoImp.queryId(Integer.parseInt(value));
        }
        else  {
            user = userDaoImp.queryName(value);
        }
        if (user == null){
            jsonObject.put("result",0);
            jsonObject.put("username","null");
            jsonObject.put("id","null");
            jsonObject.put("pos","null");
            jsonObject.put("team","null");
            out.print(jsonObject);
            return ;
        }
        jsonObject.put("result",1);
        jsonObject.put("username",user.getUsername());
        jsonObject.put("id",user.getId());
        jsonObject.put("pos",user.getPosition());
        jsonObject.put("team",user.getTid());
        out.print(jsonObject.toString());
    }
    @Override
    public void destroy() {
        if (userDaoImp!=null){
            userDaoImp = null;
        }
    }
}
