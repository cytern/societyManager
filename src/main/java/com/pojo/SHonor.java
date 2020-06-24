package com.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * s_honor  社团荣誉表
 * @data 注解，lombook插件，可以自动生成构造方法与getset方法
 */
@Data
public class SHonor implements Serializable {
    /**
     * 荣誉id
     **/
    private Integer sHonorId;
    /**
     * 荣誉名
     **/
    private String sHonorName;
    /**
     * 社团id
     **/
    private Integer societyId;
    /**
     * 创建时间
     **/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cTime;

    private static final long serialVersionUID = 1L;
}