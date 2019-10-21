package com.org.blogs.service.impl;

import com.org.blogs.entity.Photos;
import com.org.blogs.mapper.PhotosMapper;
import com.org.blogs.service.PhotoService;
import com.org.blogs.util.PageRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxy on 2019/9/11.
 */
@Service("PhotoService")
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotosMapper mapper;

    @Override
    public List<Photos> getPhotos(PageRoll page) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("startRow", page.getStartRow());
        param.put("rowNum", page.getPageSize());

        page.setParams(param);
        return mapper.getPhotos(page);
    }
}
