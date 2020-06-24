package com.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//获取url工具类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetImgUrl {
    private String baseUrl;
    private String windowsBaseIp="http://localhost:8080/";
    private String downloadUrl = "everyone/downLoad/";
    private String windowsStorage="C:\\Imgs\\";
    private String export;
    private String storage;

    public String getBaseUrl(){
           this.export = windowsBaseIp + downloadUrl;
           this.storage = windowsStorage;
        return null;
    }
}
