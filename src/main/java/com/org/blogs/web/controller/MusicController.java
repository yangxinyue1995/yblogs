package com.org.blogs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yxy on 2019/9/11.
 */
@Controller
@RequestMapping("music")
public class MusicController {

    @RequestMapping("/index")
    public String musicIndex(){
        return "frontPages/music/music";
    }
}
