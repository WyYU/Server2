package com.wyy.server.UserServer;

import com.wyy.dao.UserDaoImp;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/3/28 0028.
 */
public class Upheadimg extends HttpServlet {
    UserDaoImp userDaoImp;

    @Override
    public void init() throws ServletException {
        userDaoImp = UserDaoImp.getInstance();
        super.init();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter o = response.getWriter();
        String uid = request.getParameter("uid");
        String filename = "headpic";
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
            content =  "{'code':'0', 'msg':'图片格式错误，上传失败'}";
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
        String oldpath = userDaoImp.queryId(Integer.parseInt(uid)).getImagepatch().split("/")[1];
        System.out.println("oldpath  "+oldpath);

        File oldfile =new File(path2,oldpath);
        if (oldfile.exists()){
            oldfile.delete();
            System.out.println("deloldfile "+oldpath);
        }

        Date date = new Date();
        String timestrap = String.valueOf(date.getTime());
        String picName = "head_"+uid.toString()+timestrap+".jpg";
        File pic = new File(path2,picName);
        try {
            fileItems.get(0).write(pic);
        } catch (Exception e){
            content =  "{'code':'0', 'msg':'上传服务器失败'}";
            o.print(content);
        }
        System.out.println("path2 "+path2);
        System.out.println("upheadimg:"+picName);
        userDaoImp.updatahead(Integer.parseInt(uid),filename+"/"+picName);
        o.print("{'code':'1', 'msg':'success'}");
    }

}
