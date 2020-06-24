package com.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * s_news
 * @author 
 */

public class SNews implements Serializable {
    private Integer newsId;

    private String newsName;
    private Date cTime;

    private String conf;
//新闻类型 /news 新闻/order 公告
    private String newsType;

    private String startName;

    private Integer startId;

    public SNews(Integer newsId, String newsName, Date cTime, String conf, String newsType, String startName, Integer startId) {
        this.newsId = newsId;
        this.newsName = newsName;
        this.cTime = cTime;
        this.conf = conf;
        this.newsType = newsType;
        this.startName = startName;
        this.startId = startId;
    }

    public SNews() {

    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public Date getcTime() {
        return cTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;
}