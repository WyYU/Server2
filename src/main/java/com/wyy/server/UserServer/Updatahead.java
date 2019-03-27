package com.wyy.server.UserServer;


import com.wyy.dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by dell on 2019/3/27 0027.
 */
public class Updatahead extends HttpServlet {
    UserDaoImp userDaoImp;

    @Override
    public void init() throws ServletException {
        userDaoImp = new UserDaoImp();
        super.init();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter o = response.getWriter();
        String path = request.getRealPath("/headpic") + "/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        File pic = new File(path + "img1.jpg");
        if (!pic.exists()) {
            pic.createNewFile();
        }
        String content = FilEUploadUtils.getFileContentFromRequest(request);
        FileOutputStream fos = new FileOutputStream(pic);
        RandomAccessFile randomAccessFile = new RandomAccessFile(pic, "rw");
        InputStream is = new ByteArrayInputStream(content.getBytes());
        randomAccessFile.seek(0);
        int x = 0;
        byte[] b = new byte[1024 * 1];
        while ((x = is.read(b)) != -1) {
            fos.write(b, 0, x);
        }
        fos.close();
        System.out.println(response.toString());
        o.print(content);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
