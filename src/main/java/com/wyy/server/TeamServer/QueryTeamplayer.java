package com.wyy.server.TeamServer;

import com.wyy.dao.TeamDaoImp;
import com.wyy.po.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Created by dell on 2019/3/12 0012.
 */
public class QueryTeamplayer extends HttpServlet {
    TeamDaoImp teamDaoImp ;
    Iterator<User> iterator;

    @Override
    public void init() throws ServletException {
        teamDaoImp = new TeamDaoImp();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        int id = Integer.parseInt(request.getParameter("tid"));
        iterator = teamDaoImp.qeryTeam(id);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        while (iterator.hasNext()){
            User user = iterator.next();
            jsonObject.put("username",user.getUsername());
            jsonObject.put("uid",user.getId());
            jsonObject.put("pos",user.getPosition());
            jsonObject.put("num",user.getNum());
            jsonObject.put("goal",user.getGoal());
            jsonObject.put("ass",user.getAssisting());
            jsonObject.put("lv",user.getLevel());
            jsonArray.add(jsonObject);
        }
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
    }

    @Override
    public void destroy() {
        if (teamDaoImp!=null){
            teamDaoImp = null;
        }
        super.destroy();
    }
}
