package cn.ymsys.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

/**
 * @author luo
 */
public class HttpClientUtil {
	private static String HTTP_CONTENT_TYPE_JSON = "application/json";

	private static Log log = LogFactory.getLog(HttpClientUtil.class);

	private HttpClientUtil() {
	}

	public static String doGetData(String url) {
		log.info("请求报文:" + url);
		String respString = null;
		DefaultHttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			respString = httpClient.execute(request, new BasicResponseHandler());
		} catch (UnsupportedEncodingException e) {
			log.error(e, e.fillInStackTrace());
		} catch (ClientProtocolException e) {
			log.error(e, e.fillInStackTrace());
		} catch (IOException e) {
			log.error(e, e.fillInStackTrace());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		log.info("响应报文:" + respString);
		return respString;
	}

	/**
	 * POST JSON 格式数据
	 * 
	 * @param url     请求地址
	 * @param data    请求数据
	 * @param charset 字符编码
	 * @return 请求成功返回字符串, 失败返回 null
	 */
	public static String doPostJsonData(String url, String data, String charset) {
		return doPostStringData(url, data, charset, HTTP_CONTENT_TYPE_JSON);
	}

	/**
	 * @param url         请求地址
	 * @param data        字符串数据
	 * @param charset     字符编码
	 * @param contentType 内容格式
	 * @return
	 */
	private static String doPostStringData(String url, String data, String charset, String contentType) {
		log.info("请求报文:" + data);
		String respString = null;
		DefaultHttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			HttpPost httpPost = new HttpPost(url);
			StringEntity requestEntity = new StringEntity(data, charset);
			requestEntity.setContentEncoding(charset);
			httpPost.setHeader("Content-type", contentType);
			httpPost.setEntity(requestEntity);
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			respString = httpClient.execute(httpPost, responseHandler);
		} catch (UnsupportedEncodingException e) {
			log.error(e, e.fillInStackTrace());
		} catch (ClientProtocolException e) {
			log.error(e, e.fillInStackTrace());
		} catch (IOException e) {
			log.error(e, e.fillInStackTrace());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		log.info("响应报文:" + respString);
		return respString;
	}
}
