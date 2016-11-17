package org.lw;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lw.rsa.Base64Utils;
import org.lw.rsa.RSAUtils;

public class TestGet {

	private static final String PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpyH9XMFEUc6rGTOllZl/Kdg1VSH9z/66VpnKrhbYAOeCHA1fDS2jgyA2/naLugC+kLVuXTM2XK12c+SmdyBRQwdxq5O0DmqXsgzfrGW0YfKkNVunUTtarcvYjdpbardKAO2PFpugP9ZU6P4lqABjkVwgzWPAAcV3Xflp1HJaZeQIDAQAB";
	

	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8080/UserManagement/user/add.do";
		// 加密
		Map map = new TreeMap();
		map.put("username", "zzz");
		map.put("password", "zzz");
		StringBuffer sb = new StringBuffer();
		for (Object obj : map.entrySet()) {
			sb.append(obj + "&");
		}
		String request = sb.substring(0, sb.length() - 1);
		// 用公钥加密
		byte[] result = RSAUtils.encryptByPublicKey(request.getBytes(), PUB_KEY);
		String encode = Base64Utils.encode(result);
		url += "?"+request+"&key="+encode;
		System.out.println(url);
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpGet get = new HttpGet(url);
		 CloseableHttpResponse response = httpclient.execute(get);
		 try {
		 //获取响应码
		 int statusCode = response.getStatusLine().getStatusCode();
		 System.out.println(statusCode);
		 //http的实体
		 HttpEntity entity = response.getEntity();
		 String string = EntityUtils.toString(entity);
		 System.out.println(string);
		 } finally {
		 response.close();
		 }
		
	}
}
