package com.org.blogs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by yxy on 2019/9/11.
 */
@Entity
public class Photos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    private int id;

    @Column
    private String imgName;

    @Column
    private String imgUrl;

    @Column
    private String imgBytes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(String imgBytes) {
        this.imgBytes = imgBytes;
    }
}
