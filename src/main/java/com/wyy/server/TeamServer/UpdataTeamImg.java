package com.wyy.server.TeamServer;

import com.wyy.dao.TeamDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2019/5/20 0020.
 */
public class UpdataTeamImg extends HttpServlet {
    TeamDaoImp teamDaoImp;

    @Override
    public void init() throws ServletException {
        teamDaoImp = new TeamDaoImp();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
