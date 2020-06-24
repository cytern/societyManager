package com.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * s_user  用户表
 * @data 注解，lombook插件，可以自动生成构造方法与getset方法
 */
@Data
public class SUser implements Serializable {
    /**
     * 用户id
      **/

    private Integer userId;
    /**
     * 用户名
     **/
    private String userName;

    /**
     * 用户密码
     **/
    private String password;
    /**
     * 用户类型
     **/
    private String userType;
    /**
     * 用户真名
     **/
    private String userRealname;
    /**
     * 版本号
     **/
    private static final long serialVersionUID = 1L;
}