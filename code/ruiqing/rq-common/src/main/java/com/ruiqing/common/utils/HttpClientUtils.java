package com.ruiqing.common.utils;

import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 */
@Component
public class HttpClientUtils {

	private static final Logger log = LoggerFactory.getLogger(HttpClientTool.class);

	private static CloseableHttpClient httpClient = HttpClientTool.getHttpClient();

	/**
	 * 执行get请求
	 * 
	 * @param url
	 * @param params
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url, Map<String, String> params, String encode) throws Exception {
		log.info("执行GET请求，URL = " + url);
		if (null != params) {
			URIBuilder builder = new URIBuilder(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.setParameter(entry.getKey(), entry.getValue());
			}
			url = builder.build().toString();
		}
		HttpGet httpGet = new HttpGet(url);
		try (CloseableHttpResponse response = httpClient.execute(httpGet);){
			if (response.getStatusLine().getStatusCode() == 200) {
				if (encode == null) {
					encode = "UTF-8";
				}
				return EntityUtils.toString(response.getEntity(), encode);
			}
		}
		return null;
	}

	public String doGet(String url, String encode) throws Exception {
		return this.doGet(url, null, encode);
	}

	public String doGet(String url) throws Exception {
		return this.doGet(url, null, null);
	}

	/**
	 * 带参数的get请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url, Map<String, String> params) throws Exception {
		return this.doGet(url, params, null);
	}

	/**
	 * 执行get请求
	 * 
	 * @param url
	 * @param params
	 * @param encode
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public String doGetFile(String url, Map<String, String> params, String encode, String filePath) throws Exception {
		log.info("执行GET请求，URL = " + url);
		if (null != params) {
			URIBuilder builder = new URIBuilder(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.setParameter(entry.getKey(), entry.getValue());
			}
			url = builder.build().toString();
		}
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		OutputStream out = null;
		InputStream in = null;
		try {
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				if (encode == null) {
					encode = "UTF-8";
				}
				Header contentHeader = response.getFirstHeader("Content-Disposition");
				String filename = null;
				if (contentHeader != null) {
					HeaderElement[] values = contentHeader.getElements();
					if (values.length != 0) {
						NameValuePair param = values[0].getParameterByName("filename");
						if (param != null) {
							filename = new String(param.getValue().toString().getBytes(), encode);
						}
					}
					HttpEntity entity = response.getEntity();
					in = entity.getContent();
					File file = new File(filePath + filename);
					if (!file.exists()) {
						file.createNewFile();
					}
					File fileFlag = new File(filePath + filename + "&flag");
					if (!fileFlag.exists()) {
						fileFlag.createNewFile();
					}
					out = new FileOutputStream(file);
					byte[] buffer = new byte[1024 * 10];
					int readLength = 0;
					while ((readLength = in.read(buffer)) > 0) {
						byte[] bytes = new byte[readLength];
						System.arraycopy(buffer, 0, bytes, 0, readLength);
						out.write(bytes);
					}
					out.flush();
					out.close();
				}
				return EntityUtils.toString(response.getEntity(), encode);
			}
		} finally {
			if (response != null) {
				response.close();
			}
			if (out != null) {
				out.close();
			}
		}
		return null;
	}

	/**
	 * 执行POST请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String doPost(String url, Map<String, String> params, String encode) throws Exception {
		log.info("执行POST请求，URL = " + url);
		HttpPost httpPost = new HttpPost(url);
		if (null != params) {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			UrlEncodedFormEntity formEntity = null;
			if (encode != null) {
				formEntity = new UrlEncodedFormEntity(parameters, encode);
			} else {
				formEntity = new UrlEncodedFormEntity(parameters);
			}
			httpPost.setEntity(formEntity);
		}
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return null;
	}

	/**
	 * 执行POST请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String doPost(String url, Map<String, String> params) throws Exception {
		log.info("执行POST请求，URL = " + url);
		HttpPost httpPost = new HttpPost(url);
		if (null != params) {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
			httpPost.setEntity(formEntity);
		}
		try (CloseableHttpResponse response = httpClient.execute(httpPost);) {
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		}
		return null;
	}

	public String doPostJson(String url, String json) throws Exception {
		log.info("执行POST请求，URL = " + url);
		HttpPost httpPost = new HttpPost(url);
		if (null != json) {
			StringEntity stringEntity = new StringEntity(json, "UTF-8");
			httpPost.setEntity(stringEntity);
		}
		try(CloseableHttpResponse response = httpClient.execute(httpPost)) {
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		}
		return null;
	}

	/**
	 * 文件上传
	 */
	public String doFileUpload(String url, Map<String, String> params, String filePath) throws Exception {
		if (null != params) {
			URIBuilder builder = new URIBuilder(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.setParameter(entry.getKey(), entry.getValue());
			}
			url = builder.build().toString();
		}
		HttpPost httpPost = new HttpPost(url);
		log.info("执行POST请求，URL = " + url);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		FileBody file = new FileBody(new File(filePath));
		multipartEntityBuilder.addPart("file", file);
		HttpEntity entity = multipartEntityBuilder.build();

		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		HttpClientUtils dd = new HttpClientUtils();
		dd.doDSPFileUpload("http://127.0.0.1:8082/mams-sasac/hadwnIndx/getByHadwnId?hadwnId=1",new HashMap<>(),"");
	}
	/**
	 * DSP文件上传
	 */
	public String doDSPFileUpload(String url, Map<String, String> params, String filePath) throws Exception {
		if (null != params) {
			URIBuilder builder = new URIBuilder(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.setParameter(entry.getKey(), entry.getValue());
			}
			url = builder.build().toString();
		}
		HttpPost httpPost = new HttpPost(url);

		log.info("执行POST请求，URL = " + url);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		FileBody file = new FileBody(new File(filePath));
		multipartEntityBuilder.addPart("files", file);
		HttpEntity entity = multipartEntityBuilder.build();

		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} finally {
			if (response != null) {
				response.close();
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
        // cm.setMaxPerRoute(new
        // HttpRoute(httpHost2),detailMaxPerRoute2);//可以有细化配置2
        // cm.setMaxPerRoute(new
        // HttpRoute(httpHost3),detailMaxPerRoute3);//可以有细化配置3
        // 细化配置结束

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {

                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return false;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        newHttpclient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig)
                .setRetryHandler(httpRequestRetryHandler).build();
        return newHttpclient;
    }
}
