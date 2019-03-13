package com.wyy.server.FieldSever;

import com.wyy.dao.FieldDaoImp;
import com.wyy.po.Field;
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
 * Created by dell on 2019/3/13 0013.
 */
public class QueryField extends HttpServlet {
    FieldDaoImp fieldDaoImp ;

    @Override
    public void init() throws ServletException {
        fieldDaoImp = new FieldDaoImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        Iterator<Field> fieldIterator = fieldDaoImp.queryall().iterator();
        JSONObject jsonObject = new JSONObject();
        JSONObject res = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        if (fieldDaoImp.queryall().size()>0) {
            res.put("result",1);
            while(fieldIterator.hasNext()){
                Field field = fieldIterator.next();
                jsonObject.put("fname",field.getName());
                jsonObject.put("address",field.getAddress());
                jsonObject.put("private",field.getPrive());
                jsonObject.put("rate",field.getRate());
                jsonArray.add(jsonObject);
            }
        }else {
            res.put("result",0);
            jsonObject.put("fname","null");
            jsonObject.put("address","null");
            jsonObject.put("private","null");
            jsonObject.put("rate","null");
            return;
        }
        jsonArray1.add(res);
        jsonArray1.add(jsonArray);
        out.print(jsonArray1);
    }
    @Override
    public void destroy() {
        if (fieldDaoImp!=null){
            fieldDaoImp = null;
        }
    }
}
