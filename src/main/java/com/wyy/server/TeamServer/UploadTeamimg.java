package com.wyy.server.TeamServer;

import com.wyy.dao.TeamDaoImp;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/5/20 0020.
 */
public class UploadTeamimg extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeamDaoImp teamDaoImp = new TeamDaoImp();
        request.setCharacterEncoding("utf-8");
        PrintWriter o = response.getWriter();
        String tid = request.getParameter("tid");
        String filename = "teampic";
        DiskFileItemFactory factory = new DiskFileItemFactory(10*1024*1024, new File("E://temp"));
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置单个文件最大值为10*1024*1024
        sfu.setFileSizeMax(10*1024*1024);
        String str = request.getServletContext().getRealPath("/");
        String content ;
        Map<String,String> map ;
        List<FileItem> fileItems = null;
        try {
            fileItems = sfu.parseRequest(new ServletRequestContext(request));
            map = new HashMap<>();
            for (int i =0 ;i<fileItems.size();i++){
                if (fileItems.get(i).isFormField()){
                    map.put(fileItems.get(i).getFieldName(),fileItems.get(i).getString("utf_8"));
                }else{

                }
            }
        } catch (FileUploadException e) {
            content =  "{'code':'0', 'msg':'格式错误，上传失败'}";
            o.print(content);
            e.printStackTrace();
        }
        //String path = request.getSession().getServletContext().getRealPath("/"+filename+"/");
        String rootpath = System.getProperty("catalina.home")+"\\webapps\\ROOT";
        System.out.println("rootpath "+rootpath);
        File root = new File(rootpath);
        if (!root.exists()){
            root.mkdir();
        }
        String path2=System.getProperty("catalina.home")+"\\webapps\\ROOT\\"+filename;
        File file = new File(path2);
        if (!file.exists()) {
            file.mkdir();
        }
        System.out.println("path2"+path2);

        String picName = "teampic_"+tid+".jpg";
        File pic = new File(path2,picName);
        try {
            fileItems.get(0).write(pic);
        } catch (Exception e){
            content =  "{'code':'0', 'msg':'field'}";
            o.print(content);
        }
        System.out.println("upteamimg:"+picName);

        teamDaoImp.updatateamimg(Integer.parseInt(tid),filename+"/"+picName);
        o.print("{'code':'1', 'msg':'success'}");
    }
}
