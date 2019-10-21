package com.org.blogs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.tools.zip.ZipEntry;  
import org.apache.tools.zip.ZipOutputStream;  
import org.springframework.stereotype.Component;

@Component
public class BatchDownloadFileUtil{

	public static String batchDownFile(String dirfile,String[] sourcefiles) {
		//生成的ZIP文件名为Demo.zip  
        //String tmpFileName = "batchFile.zip";  
        byte[] buffer = new byte[1024];  
        //Date date = new Date();
       // String strZipPath = "/weilan" + tmpFileName;  
       // sourcefiles=new String[] {"D:\\daochu.iso","D:\\daochu1.iso"};
       
        try {  
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
            		dirfile));  

          
            for (int i = 0; i < sourcefiles.length; i++) {  
                FileInputStream fis = new FileInputStream(new File(sourcefiles[i]));  
                out.putNextEntry(new ZipEntry(sourcefiles[i]));  
                //设置压缩文件内的字符编码，不然会变成乱码  
                out.setEncoding("GBK");  
                int len;  
                // 读入需要下载的文件的内容，打包到zip文件  
                while ((len = fis.read(buffer)) > 0) {  
                    out.write(buffer, 0, len);  
                }  
                out.closeEntry();  
                fis.close();
            }
            out.close();
            //this.downFile(getResponse(), tmpFileName);
        } catch (Exception e) {
            //Log.error("文件下载出错", e); 
        	e.printStackTrace();
        }
        return dirfile;

	}

	
//	 /** 
//     * 文件下载 
//     * @param response 
//     * @param str 
//     */  
//    private void downFile(HttpServletResponse response, String str) {  
//        try {  
//            String path = FilePath + str;  
//            File file = new File(path);  
//            if (file.exists()) {  
//                InputStream ins = new FileInputStream(path);  
//                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面  
//                OutputStream outs = response.getOutputStream();// 获取文件输出IO流  
//                BufferedOutputStream bouts = new BufferedOutputStream(outs);  
//                response.setContentType("application/x-download");// 设置response内容的类型  
//                response.setHeader(  
//                        "Content-disposition",  
//                        "attachment;filename="  
//                                + URLEncoder.encode(str, "UTF-8"));// 设置头部信息  
//                int bytesRead = 0;  
//                byte[] buffer = new byte[8192];  
//                // 开始向网络传输文件流  
//                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {  
//                    bouts.write(buffer, 0, bytesRead);  
//                }  
//                bouts.flush();// 这里一定要调用flush()方法  
//                ins.close();  
//                bins.close();  
//                outs.close();  
//                bouts.close();  
//            } else {  
//                response.sendRedirect("../error.jsp");  
//            }  
//        } catch (IOException e) {  
//            Log.error("文件下载出错", e);  
//        }  
//    }  
}
