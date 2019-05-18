package com.wyy.server.UserServer;

import com.wyy.dao.UserDaoImp;
import com.wyy.po.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by dell on 2019/5/18 0018.
 */
public class SearchUser extends HttpServlet {
    UserDaoImp userDaoImp ;
    @Override
    public void init() throws ServletException {
        userDaoImp = UserDaoImp.getInstance();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String value = request.getParameter("value");
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        List<User> searchres = userDaoImp.searchUser(value);
        try {
            for(int i = 0 ; i < searchres.size();i++){
                User user = searchres.get(i);
                jsonObject.put("username",user.getUsername());
                jsonObject.put("id",user.getId());
                jsonObject.put("pos",user.getPosition());
                jsonObject.put("team",user.getTid());
                jsonObject.put("num",user.getNum());
                jsonObject.put("imagepath",user.getImagepatch());
                jsonObject.put("lv",user.getLevel());
                jsonObject.put("goal",user.getGoal());
                jsonObject.put("ass",user.getAssisting());
                jsonArray.add(jsonObject);
            }
        }catch (Exception e){
            result.put("result",0);
        }
        result.put("result",1);
        jsonArray1.add(result);
        jsonArray1.add(jsonArray);
        out.print(jsonArray1.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
