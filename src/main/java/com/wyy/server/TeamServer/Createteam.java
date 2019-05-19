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
public class Createteam extends HttpServlet {
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
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        String teamname = request.getParameter("tname");
        String des = request.getParameter("desc");
        Team team = new Team();
        int restult;
        try {
            restult = teamDaoImp.createTeam(teamname);
            team = teamDaoImp.queryteambyName(teamname);
            teamDaoImp.updataTeam(team.getTid(),des);
        } catch (Exception e){
            jsonObject.put("result",0);
            return;
        }
        jsonObject.put("result",restult);
        out.print(jsonObject.toString());
    }

    @Override
    public void destroy() {
        if (teamDaoImp!=null){
            teamDaoImp = null;
        }
        super.destroy();
    }
}
