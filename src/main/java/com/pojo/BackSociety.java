package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BackSociety {
    private Integer societyId;
    //社团名
    private String societyName;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cTime;
    //简介
    private String conf;
    //目标
    private String purpose;
    //活动列表
    private List<Activity> activities;
    //荣誉列表
    private List<SHonor> sHonors;
}
