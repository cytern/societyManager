package com.controller;


import com.util.ChangeDate;
import com.util.GetImgUrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * 静态资源文件处理器/文件上传下载控制器，网上copy版本很多，即插即用，无需多解释，负责处理项目静态资源
 */
@Api("静态资源文件处理")
@RestController
public class FileController {


    ChangeDate changeDate = new ChangeDate();
    @GetMapping("testFile")
    public void getFile(){

    }

    @RequestMapping("everyone/simpleUpload")
    public HashMap<Object,Object> uploadSimple(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
        GetImgUrl getImgUrl = new GetImgUrl();
        getImgUrl.getBaseUrl();
        String path = getImgUrl.getStorage();
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }
        String tempName = file.getOriginalFilename();
        String tempName1 = tempName.substring(tempName.indexOf("."));
        String fileName = String.valueOf(changeDate.getDate().getTime()) + tempName1;

        file.transferTo(new File(realPath+"/" + fileName));
        HashMap<Object,Object> hashMap = new HashMap<>();
        return hashMap;
    }
     @ApiOperation("获取图片")
    @RequestMapping("everyone/downLoad/{fileName:.+}")
    public String downLoads(HttpServletResponse response, HttpServletRequest request, @PathVariable("fileName") String fileName) throws IOException {
//        String path = "/export/data/img";
        GetImgUrl getImgUrl = new GetImgUrl();
        getImgUrl.getBaseUrl();
        String path = getImgUrl.getStorage();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        File file =new File(path,fileName);

        InputStream input = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buff = new byte[1024];
        int index =0;
        while ((index=input.read(buff))!=-1){
            out.write(buff,0,index);
            out.flush();
        }
        out.close();
        input.close();
        return null;

    }

}
