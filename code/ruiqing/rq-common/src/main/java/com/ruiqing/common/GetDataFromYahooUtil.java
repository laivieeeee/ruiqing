package com.ruiqing.common;

import com.ruiqing.common.utils.HttpClientUtils;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.TimeUnit;


/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/6 14:19
 */
public class GetDataFromYahooUtil {
    private static CloseableHttpClient httpClient = HttpClientTool.getHttpClient();

    public static void main(String[] args) throws Exception {
        String url = "http://hq.sinajs.cn/list=sh600030";
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        HttpGet post = new HttpGet(url);
        post.setHeader("Content-Type", "application/json");
        while (true) {
            TimeUnit.MILLISECONDS.sleep(5000);
            response = client.execute(post);
            String	ret      = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(">>>>>"+ret);
            String[] str = ret.split(",");


        }
    }

    public static String doGet(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(httpGet);) {
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        }
        return null;
    }

}

class HttpClientTool {

    private static CloseableHttpClient httpClient = null;

    private static final int connectionRequestTimeout = Integer.parseInt("30000");
    private static final int connectTimeout = Integer.parseInt("5000");
    private static final int socketTimeout = Integer.parseInt("30000");
    private static final int maxTotal = Integer.parseInt("200");
    private static final int maxPerRoute = Integer.parseInt("100");

    // 细化配置参数,用来对每路参数做精细化处理,可以管控各ip的流量,比如默认配置请求baidu:80端口最大100个并发链接,
    private static final String detailHostName = "http://www.baidu.com";// 每个细化配置之ip(不重要,在特殊场景很有用)
    private static final int detailPort = 80;// 每个细化配置之port(不重要,在特殊场景很有用)
    private static final int detailMaxPerRoute = 100;// 每个细化配置之最大并发数(不重要,在特殊场景很有用)

    public static CloseableHttpClient getHttpClient() {
        if (null == httpClient) {
            synchronized (HttpClientUtils.class) {
                if (null == httpClient) {
                    httpClient = init();
                }
            }
        }
        return httpClient;
    }

    /**
     * 链接池初始化 让CloseableHttpClient 一直活在池的世界里, 但是HttpPost却一直用完就消掉,这样可以让链接一直保持着.
     *
     * @return
     */
    private static CloseableHttpClient init() {

        CloseableHttpClient newHttpclient = null;
        // 设置连接池
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf).register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(maxPerRoute);

        // 细化配置开始,其实这里用Map或List的for循环来配置每个链接,在特殊场景很有用.
        // 将每个路由基础的连接做特殊化配置,一般用不着
        HttpHost httpHost = new HttpHost(detailHostName, detailPort);
        // 将目标主机的最大连接数增加
        cm.setMaxPerRoute(new HttpRoute(httpHost), detailMaxPerRoute);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        };

        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        newHttpclient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig)
                .setRetryHandler(httpRequestRetryHandler).build();
        return newHttpclient;
    }
}