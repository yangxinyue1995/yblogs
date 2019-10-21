package com.org.blogs.service;

import com.org.blogs.entity.Photos;
import com.org.blogs.util.PageRoll;

import java.util.List;

/**
 * Created by yxy on 2019/9/11.
 */
public interface PhotoService {

    List<Photos> getPhotos(PageRoll page);
}
