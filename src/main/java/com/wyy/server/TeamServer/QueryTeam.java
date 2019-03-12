package com.wyy.server.TeamServer;

import com.wyy.dao.TeamDaoImp;
import com.wyy.po.Team;
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
public class QueryTeam extends HttpServlet {
    TeamDaoImp teamDaoImp ;

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
        response.setHeader("Cache-Control","no-cache");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        Team team ;
        try {
            if (key.equals("id")){
                team = teamDaoImp.queryteambyid(Integer.parseInt(value));
            } else {
                team = teamDaoImp.queryteambyName(value);
            }
            if (team == null) {
                jsonObject.put("result",0);
                out.print(jsonObject);
                return;
            }
            jsonObject.put("teamname",team.getTname());
            jsonObject.put("tid",team.getTid());
            jsonObject.put("createtime",team.getCreateTime());
            jsonObject.put("introduce",team.getIntroduce());
            jsonObject.put("colorcode",team.getColorcode());
            jsonObject.put("result",1);
        } catch (Exception e){
            jsonObject.put("result",0);
            return;
        }
        jsonObject.put("result",1);
        out.print(jsonObject);
    }

    @Override
    public void destroy() {
        if (teamDaoImp!=null){
            teamDaoImp = null;
        }
        super.destroy();
    }

}
