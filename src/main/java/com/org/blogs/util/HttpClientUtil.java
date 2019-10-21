package com.org.blogs.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientUtil {

	//private static final int DEFAULT_MAX_PER_ROUTE = 300;
	private static final int MAX_TOTAL = 100;
	private static final int CONNECTION_TIMEOUT_MILLIS = 1000 * 60 * 10;
	private static final int SOCKET_TIMEOUT_MILLIS = 1000 * 60 * 10;

	private static final PoolingHttpClientConnectionManager cm;
	private static CloseableHttpClient conn = null;

	static {

		cm = new PoolingHttpClientConnectionManager();
		cm.setDefaultMaxPerRoute(MAX_TOTAL);
		cm.setMaxTotal(MAX_TOTAL);
		conn = (CloseableHttpClient) getHttpClient();
	}

	public static HttpClient getHttpClient() {
		RequestConfig config = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT_MILLIS).setConnectTimeout(CONNECTION_TIMEOUT_MILLIS).build();
		return HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).build();

	}
	
	public static HttpClient getHttpClient(int scoketTimeout) {
		RequestConfig config = RequestConfig.custom().setSocketTimeout(scoketTimeout).setConnectTimeout(CONNECTION_TIMEOUT_MILLIS).build();
		return HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).build();

	}

	/**
	 * httpclient 方式get请求
	 * 
	 * @param URL
	 * @param headers 请求头
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static String getClientInvoke(String Url, Map<String, String> headers, String charSet) {

		HttpGet httpGet = new HttpGet(Url);
		String result = "";
		try {
			// 设置请求头
			if (headers != null) {
				for (Entry<String, String> entry : headers.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}

			HttpResponse response = conn.execute(httpGet);
			result = printResponse(response, charSet);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			httpGet.abort();
		}

		return null;
	}
	
	/**
	 * httpclient 方式post请求
	 * 
	 * @param URL
	 * @param headers 请求头
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static String getClientPost(String Url, Map<String, String> headers, String charSet) {

		HttpPost httpGet = new HttpPost(Url);
		String result = "";
		try {
			// 设置请求头
			if (headers != null) {
				for (Entry<String, String> entry : headers.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}

			HttpResponse response = conn.execute(httpGet);
			result = printResponse(response, charSet);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			httpGet.abort();
		}

		return null;
	}
	
	/**
	 * httpclient 方式post请求
	 * 
	 * @param URL
	 * @param headers 请求头
	 * @param param 请求参数
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static String getClientPost(String Url, Map<String, String> headers, Map<String, Object> param, String charSet) {

		HttpPost httpPost = new HttpPost(Url);
		String result = "";
		try {
			// 设置请求头
			if (headers != null) {
				for (Entry<String, String> entry : headers.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}
			
			if(param!=null && !param.isEmpty()){
				
				MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
				
				for (Entry<String, Object> entry : param.entrySet()) {
					if (entry.getValue() instanceof String) {
						reqEntity.addTextBody(entry.getKey(), entry.getValue().toString());
					} else if (entry.getValue() instanceof Integer) {
						reqEntity.addTextBody(entry.getKey(), entry.getValue().toString());
					} else if (entry.getValue() instanceof byte[]) {
						reqEntity.addBinaryBody(entry.getKey(), new ByteArrayInputStream((byte[]) entry.getValue()), ContentType.DEFAULT_BINARY, "img.jpg");
					}
				}
				
				httpPost.setEntity(reqEntity.build());
			}

			HttpResponse response = conn.execute(httpPost);
			result = printResponse(response, charSet);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			httpPost.abort();
		}

		return null;
	}

	/**
	 * Java Url 方式get请求
	 * 
	 * @param URL
	 * @param headers 请求头
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static String getURLInvoke(String Url, Map<String, String> headers, String charSet) {

		BufferedReader bufReader = null;
		InputStreamReader input = null;
		try {
			URL url = new URL(Url);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(CONNECTION_TIMEOUT_MILLIS);
			// 设置请求头
			if (headers != null) {
				for (Entry<String, String> entry : headers.entrySet()) {
					httpConn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			input = charSet == null ? new InputStreamReader(httpConn.getInputStream()) : new InputStreamReader(httpConn.getInputStream(), charSet);

			bufReader = new BufferedReader(input);
			String line = "";
			StringBuilder contentBuf = new StringBuilder();
			while ((line = bufReader.readLine()) != null) {
				contentBuf.append(line);
			}
			String buf = contentBuf.toString();
			return buf;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private static String printResponse(HttpResponse response, String charSet) throws Exception {
		HttpEntity entity = response.getEntity();
		StringBuilder lines = new StringBuilder();
		BufferedReader is = null;
		try {
			is = charSet == null ? new BufferedReader(new InputStreamReader(entity.getContent())) : new BufferedReader(new InputStreamReader(entity.getContent(), charSet));
			String line = null;
			while ((line = is.readLine()) != null) {
				lines.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
		}

		return lines.toString();
	}

}
