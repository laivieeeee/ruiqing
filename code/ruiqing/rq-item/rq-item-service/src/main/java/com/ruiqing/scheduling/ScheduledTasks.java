package com.ruiqing.scheduling;

import com.ruiqing.entity.Money;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Lai JianZheng拼音
 * @Date: 2020/5/27 15:28
 */
@Component
public class ScheduledTasks {

    public static void main(String[] args) throws Exception {
        taskCustomReports();
    }

    //@Autowired
    //private RuiqingMapper ruiqingMapper;
    //@Scheduled(cron = "10 * * * * ?")
    public static void taskCustomReports() throws Exception {
        String url = "http://hq.sinajs.cn/list=sh600030,sz002792";
        while (true) {
            // 请求返回数据
            String ret = req(url);
            String[] gpdm = ret.split(";");
            for (int j = 0; j < gpdm.length - 1; j++) {
                String[] gpmx = gpdm[j].split(",");
                Money dto = dd(gpmx);
                // （最新-开盘）开盘*100
                BigDecimal multiply = new BigDecimal(dto.getDqjg()).subtract(new BigDecimal(dto.getZrspj()));
                System.out.print("傻逼" + multiply.divide(new BigDecimal(dto.getZrspj()), 5).multiply(new BigDecimal("100")).toString() + "-");
                System.out.println(dto.toString());
            }
        }
    }

    private static String req(String url) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        HttpGet post = new HttpGet(url);
        post.setHeader("Content-Type", "application/json");
        // 请求返回数据
        TimeUnit.MILLISECONDS.sleep(2000);
        response = client.execute(post);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

    private static Money dd(String[] gpmx) {
        Money dto = new Money();
        dto.setZrspj(gpmx[2]);
        dto.setDqjg(gpmx[3]);
        dto.setTime(gpmx[gpmx.length - 2]);
        dto.setJrzgj(gpmx[4]);
        dto.setJrzdj(gpmx[5]);
        dto.setMr1(gpmx[6]);
        dto.setMc1(gpmx[7]);
        dto.setCjgps(new BigDecimal(gpmx[8]).divide(new BigDecimal("100")).toString());
        dto.setCjjr(new BigDecimal(gpmx[9]).divide(new BigDecimal("10000")).divide(new BigDecimal("10000")).toString());
        dto.setM1g(new BigDecimal(gpmx[10]).divide(new BigDecimal("100")).toString());
        dto.setM1j(gpmx[11]);
        dto.setM2g(new BigDecimal(gpmx[12]).divide(new BigDecimal("100")).toString());
        dto.setM2j(gpmx[13]);
        dto.setM3g(new BigDecimal(gpmx[14]).divide(new BigDecimal("100")).toString());
        dto.setM3j(gpmx[15]);
        dto.setM4g(new BigDecimal(gpmx[16]).divide(new BigDecimal("100")).toString());
        dto.setM4j(gpmx[17]);
        dto.setM5g(new BigDecimal(gpmx[18]).divide(new BigDecimal("100")).toString());
        dto.setM5j(gpmx[19]);
        dto.setC1g(new BigDecimal(gpmx[20]).divide(new BigDecimal("100")).toString());
        dto.setC1j(gpmx[21]);
        dto.setC2g(new BigDecimal(gpmx[22]).divide(new BigDecimal("100")).toString());
        dto.setC2j(gpmx[23]);
        dto.setC3g(new BigDecimal(gpmx[24]).divide(new BigDecimal("100")).toString());
        dto.setC3j(gpmx[25]);
        dto.setC4g(new BigDecimal(gpmx[26]).divide(new BigDecimal("100")).toString());
        dto.setC4j(gpmx[27]);
        dto.setC5g(new BigDecimal(gpmx[28]).divide(new BigDecimal("100")).toString());
        dto.setC5j(gpmx[29]);
        return dto;
    }
}
