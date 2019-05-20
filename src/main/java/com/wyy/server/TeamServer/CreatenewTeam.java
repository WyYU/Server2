package com.wyy.server.TeamServer;

import com.wyy.dao.TeamDaoImp;
import com.wyy.dao.UserDaoImp;
import com.wyy.po.Team;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2019/5/20 0020.
 */
public class CreatenewTeam extends HttpServlet {
    private TeamDaoImp teamDaoImp;
    private UserDaoImp userDaoImp;

    @Override
    public void init() throws ServletException {
        teamDaoImp = new TeamDaoImp();
        userDaoImp = new UserDaoImp();
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
        String uid = request.getParameter("uid");
        String teamname = request.getParameter("tname");
        String des = request.getParameter("desc");
        Team team = new Team();
        int restult;
        try {
            restult = teamDaoImp.createTeam(teamname);
            team = teamDaoImp.queryteambyName(teamname);
            teamDaoImp.updataTeam(team.getTid(),des,"1","path");
            System.out.println("添加球队描述");
            userDaoImp.joinTeam(Integer.parseInt(uid),team.getTid());
            userDaoImp.levelChange(Integer.parseInt(uid),5);
        } catch (Exception e){
            jsonObject.put("result",0);
            jsonObject.put("tid",0);
            return;
        }
        jsonObject.put("result",restult);
        jsonObject.put("tid",team.getTid());
        out.print(jsonObject.toString());
    }
}
