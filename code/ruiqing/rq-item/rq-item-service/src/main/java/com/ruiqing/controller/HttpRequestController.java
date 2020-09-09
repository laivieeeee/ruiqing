package com.ruiqing.controller;


import com.ruiqing.vo.FileVo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/18 14:05
 */
@RestController
@RequestMapping("/request/http")
public class HttpRequestController {

    @RequestMapping("/getFile")
    public FileVo getFile() {
        FileVo fileVo = new FileVo();
        fileVo.setName("ddd");
        try {

            File file = new File("D:\\Work\\SASAC\\basePath\\hadwn\\dbs\\20200602094601.db");
            //FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
            //IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
            //fileVo.setFile(new CommonsMultipartFile(fileItem));
        } catch (Exception e) {

        }
        return fileVo;
    }

    @RequestMapping("/test")
    public void test() throws Exception {
        HttpPost post = new HttpPost("http://127.0.0.1:5860//request/http/getFile");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        post.setHeader("Content-Type", "application/json");
        // 请求返回数据
        TimeUnit.MILLISECONDS.sleep(3000);
        response = client.execute(post);
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        System.out.println(response);
    }
}
