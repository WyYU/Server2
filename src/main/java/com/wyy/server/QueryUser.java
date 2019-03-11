package com.wyy.server;

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
        String id = req.getParameter("id");
        User user = userDaoImp.queryId(Integer.parseInt(id) );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",user.getUsername());
        jsonObject.put("id",user.getId());
        jsonObject.put("pos",user.getPosition());
        jsonObject.put("team",user.getTid());
        out.print(jsonObject.toString());
    }
}
