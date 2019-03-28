package com.wyy.server.UserServer;


import com.wyy.dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2019/3/27 0027.
 */
public class Updatahead extends HttpServlet {
    UserDaoImp userDaoImp;

    @Override
    public void init() throws ServletException {
        userDaoImp = UserDaoImp.getInstance();
        super.init();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        PrintWriter o = response.getWriter();
//        String uid = request.getParameter("uid");
//        String filename = "headpic";
//        DiskFileItemFactory factory = new DiskFileItemFactory(10*1024*1024, new File("E://temp"));
//        ServletFileUpload sfu = new ServletFileUpload(factory);
//        //设置单个文件最大值为10*1024*1024
//        sfu.setFileSizeMax(10*1024*1024);
//        String str = request.getServletContext().getRealPath("/");
//        String content ;
//        int loc = str.indexOf("target");
//        Map<String,String> map = new HashMap<>();
//        List<FileItem> fileItems = null;
//        try {
//            fileItems = sfu.parseRequest(new ServletRequestContext(request));
//            map = new HashMap<>();
//            for (int i =0 ;i<fileItems.size();i++){
//                if (fileItems.get(i).isFormField()){
//                map.put(fileItems.get(i).getFieldName(),fileItems.get(i).getString("utf_8"));
//                }else{
//
//                }
//            }
//        } catch (FileUploadException e) {
//            content =  "{'code':'0', 'msg':'图片格式错误，上传失败'}";
//            o.print(content);
//            e.printStackTrace();
//        }
//        String path = request.getRealPath("/"+filename+")" + "/");
//        File file = new File(path);
//        if (!file.exists()) {
//            file.mkdir();
//        }
//        File pic = new File(path,"head_"+uid.toString()+".jpg");
//        try {
//            fileItems.get(0).write(pic);
//        } catch (Exception e){
//           content =  "{'code':'0', 'msg':'上传至服务器失败'}";
//            o.print(content);
//        }
//        System.out.println("head_"+uid.toString()+".jpg");
//        userDaoImp.updatahead(Integer.parseInt(uid),filename+"/"+"head_54.jpg");
//        o.print("{'code':'1', 'msg':'成功'}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
