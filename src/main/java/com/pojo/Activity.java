package com.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.prism.PixelFormat;
import lombok.Data;

import javax.naming.Name;

/**
 * activity  活动表
 * @data 注解，lombook插件，可以自动生成构造方法与getset方法
 */
@Data
public class Activity implements Serializable {
    /**
     * 活动id
     **/
    private Integer activityId;
    /**
     * 活动名
     **/
    private String activityName;
    /**
     * 开始时间
     **/

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sTime;
    /**
     * 结束时间
     **/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date eTime;
    /**
     * 介绍
     **/
    private String conf;
    /**
     * 社团id
     **/
    private Integer societyId;

    private static final long serialVersionUID = 1L;
}