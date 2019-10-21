package com.org.blogs.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.org.blogs.util.ActionEnter;
import com.org.blogs.util.R;
import net.sf.json.util.JSONUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

/**
 * Created by yxy on 2019/9/23.
 */
@Controller
@RequestMapping("image")
public class ImageController {

    @RequestMapping("/saveImage")
    @ResponseBody
    public void saveImage(@RequestParam(value = "Image") MultipartFile imgFile, HttpServletRequest request){
        try{
            String filePath = request.getServletContext().getRealPath("/")+"上传图片\\";
            imgFile.getOriginalFilename();
            File outFile = new File(filePath);
            if (!outFile.exists()) {
                outFile.mkdirs();
            }
            File file = new File(filePath);
            imgFile.transferTo(file);

            String suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
            BufferedImage bi = ImageIO.read(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(bi, suffix, out);
        }catch (Exception e){

        }
    }

    /**
     * ueditor 获取配置文件
     * @param request
     * @return
     */
    @RequestMapping("/config")
    @ResponseBody
    public Object getConfigJson(HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer(request.getServletContext()
                .getRealPath("/"));
        buffer.append("\\resources\\utf8-jsp\\jsp\\config.json");
        String str = new ActionEnter(request, buffer.toString()).exec();
        Map map = JSONObject.toJavaObject(JSONObject.parseObject(str),Map.class);
        return map;
    }
}
