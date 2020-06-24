package com.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * society 社团
 * @data 注解，lombook插件，可以自动生成构造方法与getset方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Society implements Serializable {

    private Integer societyId;
//社团名
    private String societyName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date cTime;
//社团介绍
    private String conf;
//社团目标
    private String purpose;

    private String imgUrl;

    private static final long serialVersionUID = 1L;
}