package com.ruiqing.entity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/6 15:47
 */

public class Shabi {
    public static void main(String[] args) throws Exception {
        String url = "http://hq.sinajs.cn/list=sh600030";
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        HttpGet post = new HttpGet(url);
        post.setHeader("Content-Type", "application/json");
        while (true) {
            TimeUnit.MILLISECONDS.sleep(3000);
            response = client.execute(post);
            String ret = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(">>>>>" + ret);
            String[] str = ret.split(",");
            Money money = new Money();
            int i = 0;
            money.setName(str[i]);
        }
    }
}
