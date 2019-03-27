package com.wyy.server.UserServer;

/**
 * Created by dell on 2019/3/27 0027.
 */
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
public class FilEUploadUtils {
    public static String getFileContentFromRequest(HttpServletRequest request){
        try{
            request.setCharacterEncoding("utf-8");
            if(ServletFileUpload.isMultipartContent(request)) {
                DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();// 创建该对象
                ServletFileUpload fileUpload=new ServletFileUpload(diskFileItemFactory);// 创建该对象
                FileItemIterator itemIterator=fileUpload.getItemIterator(request);// 解析request 请求,并返回FileItemIterator集合
                StringBuffer fileContent=new StringBuffer();
                while(itemIterator.hasNext()) {
                    FileItemStream itemStream=itemIterator.next();//从集合中获得一个文件流
                    if(!itemStream.isFormField() && itemStream.getName().length() > 0) {    //过滤掉表单中非文件
                        BufferedInputStream inputStream=new BufferedInputStream(itemStream.openStream());   //获得文件输入流
                        inputStream.mark(itemStream.openStream().available()+1);
                        //判断上传的文件的编码格式 支持 UTF-8 和 ANSI两种类型
                        String charsetName=getFileCharsetName(inputStream);

                        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,charsetName));
                        String lineTxt=null;
                        while((lineTxt=bufferedReader.readLine()) != null) {
                            fileContent.append(lineTxt+"\r");
                        }

                    }
                }
                return fileContent.toString();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //判断上传的文件的编码格式 支持 UTF-8 和 ANSI两种类型
    private static String getFileCharsetName(InputStream inputStream) throws IOException{
        String charsetName="GBK";
        byte[] typeByte = new byte[3];

        inputStream.read(typeByte);
        if (typeByte[0] == -17 && typeByte[1] == -69 && typeByte[2] == -65){
            charsetName="UTF-8";
        }
        else{
            charsetName="GBK";
        }
        inputStream.reset();
        return charsetName;
    }

}
