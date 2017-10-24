package com.ele.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http 请求工具类
 * 
 * @author yaoxj
 * @time 2017年5月12日上午11:26:40
 */
public class HttpServletUtils {
	private static Logger logger = LoggerFactory
			.getLogger(HttpServletUtils.class);

	/**
	 * post请求方式请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String requestPost(String url, List<NameValuePair> params)
			throws ClientProtocolException, IOException {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
		CloseableHttpResponse response = httpclient.execute(post);
		StatusLine statusLine = response.getStatusLine();

		if (statusLine.getStatusCode() == 200) {
			HttpEntity httpEntity = response.getEntity();
			result = EntityUtils.toString(httpEntity);
		} else {
			throw new IOException(StringUtils.stringConcatenation(
					"请求失败！请求链接为：", url, "，返回的请求代码为：",
					statusLine.getStatusCode() + "", "，错误原因为:",
					statusLine.getReasonPhrase()));
		}

		return result;
	}

	/**
	 * post请求方式
	 * 
	 * @param url
	 * @param map
	 * @return string
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String requestPost(String url, Map<String, String> map)
			throws ClientProtocolException, IOException {
		return requestPost(url, toNameValuePairs(map));
	}

	/**
	 * 参数准备
	 * 
	 * @param map
	 * @return
	 */
	public static List<NameValuePair> toNameValuePairs(Map<String, String> map) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		for (Entry<String, String> entry : map.entrySet()) {
			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return params;
	}

	public static String reqeustGet(String requestUrl)
			throws ClientProtocolException, IOException {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// System.out.println(requestUrl);
		logger.info("http请求地址是:{}", requestUrl);
		HttpGet get = new HttpGet(requestUrl);
		CloseableHttpResponse response = httpclient.execute(get);

		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() == 200) {
			HttpEntity httpEntity = response.getEntity();
			result = EntityUtils.toString(httpEntity);
		} else {
			throw new IOException(StringUtils.stringConcatenation(
					"请求失败！请求链接为：", requestUrl, "，返回的请求代码为：",
					statusLine.getStatusCode() + "", "，错误原因为:",
					statusLine.getReasonPhrase()));
		}

		return result;

	}

	public static String reqeustPost(String requestUrl)	throws ClientProtocolException, IOException {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		logger.info("http请求地址是:{}", requestUrl);
		HttpPost post = new HttpPost(requestUrl);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		// BasicHeader("Cookie","dxadmin=B611CDA77DB58E7201A98953C83897B46AD1DAA9D0EA5964C9D375ECA47F43AF"));
		CloseableHttpResponse response = httpclient.execute(post);
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() == 200
				|| statusLine.getStatusCode() == 401) {
			HttpEntity httpEntity = response.getEntity();
			result = EntityUtils.toString(httpEntity);
		} else {
			throw new IOException(StringUtils.stringConcatenation(
					"请求失败！请求链接为：", requestUrl, "，返回的请求代码为：",
					statusLine.getStatusCode() + "", "，错误原因为:",
					statusLine.getReasonPhrase()));
		}

		return result;

	}

	public static String httpPost(String url, String invoices) {
		// get请求返回结果
		// String invoices = null;
		PostMethod post = new PostMethod(url);
		int statusCode = 0;
		String result = null;
		try {
			HttpClient client = new HttpClient();
			org.apache.commons.httpclient.NameValuePair pair = new org.apache.commons.httpclient.NameValuePair(
					"invoices", invoices);
			post.addParameter(pair);
			logger.info("发送内容为:" + invoices);
			// 执行postMethod
			statusCode = client.executeMethod(post);
			/** 请求发送成功，并得到响应 **/
			if (statusCode == 200) {
				logger.info("post请求提交成功:" + url);
				result = post.getResponseBodyAsString();
				logger.info("响应内容为:" + result);
			} else {
				logger.error("post请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			post.releaseConnection();
		}
		return result;
	}

	/**
	 * post 方法
	 * @param url
	 * @param map
	 * @return string
	 * @throws Exception
	 */
	public String doPost(String url, Map<String, String[]> map)	throws Exception {
		HttpPost httpost = new HttpPost(url);
		List nvps = new ArrayList();
		if (map != null) {
			for (Map.Entry entry : map.entrySet()) {
				String key = (String) entry.getKey();
				String[] value = (String[]) entry.getValue();
				NameValuePair nvp = new BasicNameValuePair(key.toString(),value[0]);
				nvps.add(nvp);
			}
		}
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpost);
		HttpEntity entity = response.getEntity();
		InputStream returnStream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(returnStream, "utf-8"));

		StringBuilder result = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str);
		}
		String text = result.toString();
		return text;
	}
	
	/**
	 * post 方法
	 * @param url
	 * @param map
	 * @return string
	 * @throws Exception
	 */
	public static String doPost(String url, List <NameValuePair> nvps)	throws Exception {
		HttpPost httphost = new HttpPost(url);
		httphost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		CloseableHttpClient httpclient = HttpClients.createDefault();
		logger.info("httphost:" + httphost);
		CloseableHttpResponse response = httpclient.execute(httphost);
		HttpEntity entity = response.getEntity();
		InputStream returnStream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(returnStream, "utf-8"));

		StringBuilder result = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str);
		}
		String text = result.toString();
		return text;
	}

}
