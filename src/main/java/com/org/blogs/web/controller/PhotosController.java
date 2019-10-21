package com.org.blogs.web.controller;

import com.org.blogs.entity.Photos;
import com.org.blogs.service.PhotoService;
import com.org.blogs.util.PageRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by yxy on 2019/9/11.
 */
@Controller
@RequestMapping("photos")
public class PhotosController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping("/index")
    public String photoIndex(Model model, HttpServletRequest request){

        PageRoll page = new PageRoll();
        page.setPageNo(1);
        page.setPageSize(5);

        List<Photos> photos = photoService.getPhotos(page);
        model.addAttribute("photos",photos);
        model.addAttribute("totalPage", page.getTotalPage());
        model.addAttribute("totalRows", page.getTotalRows());
        model.addAttribute("nextPage", 1);

        return "frontPages/photos/photo";
    }

    @RequestMapping("/getImages")
    public String getImages(Model model, HttpServletRequest request){

        String pageNo = request.getParameter("page");
        int pages=1;
        if(!pageNo.equals("")){
            pages = pageNo != null && Integer.parseInt(pageNo) >=0 ? Integer.parseInt(pageNo) : 1;
        }
        PageRoll page = new PageRoll();
        page.setPageNo(pages);
        page.setPageSize(5);

        List<Photos> photos = photoService.getPhotos(page);
        model.addAttribute("photos",photos);
        model.addAttribute("totalPage", page.getTotalPage());
        model.addAttribute("totalRows", page.getTotalRows());
        if(photos.size()>0){
            model.addAttribute("nextPage", pages+1);
        } else {
            model.addAttribute("nextPage", 0);
        }
        return "frontPages/photos/photo";
    }

    @RequestMapping(value = "/showImg")
    @ResponseBody
    public void showImage(HttpServletRequest request, HttpServletResponse response, String pathName)throws IOException{
        File imgFile = new File(pathName);
        FileInputStream fin = null;
        OutputStream output=null;
            try {
                output = response.getOutputStream();
                fin = new FileInputStream(imgFile);
                byte[] arr = new byte[1024*10];
                int n;
                while((n=fin.read(arr))!= -1){
                    output.write(arr, 0, n);
                }
                output.flush();
                } catch (IndexOutOfBoundsException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            try {
                output.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
