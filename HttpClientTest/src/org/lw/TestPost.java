package org.lw;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.lw.rsa.Base64Utils;
import org.lw.rsa.RSAUtils;

public class TestPost {
	private static final String PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1xllM3B6DQOCitl4uJdiQGbrP4FhpgMs15JxK1Fsyk5Jh7AjDMLVVVr2uCQ1+efCj9FEqIwAVeIj7e1pGaZ3NB9hFKQUjBiRDNqhlhkE3qr96rl4EeqGgyYYRK3AKBG7adpRfz45TPcPdsvuSdAaXflxYAlNJqRIpqvNV7ZFZUQIDAQAB";
	public static final String PRI_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALXGWUzcHoNA4KK2Xi4l2JAZus/gWGmAyzXknErUWzKTkmHsCMMwtVVWva4JDX558KP0USojABV4iPt7WkZpnc0H2EUpBSMGJEM2qGWGQTeqv3quXgR6oaDJhhErcAoEbtp2lF/PjlM9w92y+5J0Bpd+XFgCU0mpEimq81XtkVlRAgMBAAECgYANMx0S9r0EfPaS+jk2kqr6M9Qb+keRSbf37uTmjisdLhgoIjK0sdnJG6Zkd+XR9FrkHP6z15zO8lTIJjn0+QBrXrQZZL/4v6h5H+tAPmymP7WA7L4vjNaEcfys9lrtQgnf5tXI3bMwBW3axwiN8FBBuQU1fHuEbVvA7+GZSIFqEQJBAOIzHIxkE/oubIeMW291UredBCeP/JHeRUp1+Issu5prgfKm5PpPAbAh8INl9Hl5mnNNyZxSo5KLH5K/OtVB3X0CQQDNuPL9D1xrsxfp4oawz5+lM8AMUAhI1VzeKX03iMYNGxJU0nAn7IviocEHKw4XDOfjQPyVLbtlihb4faG7loNlAkEAoPk8tNA3w1AsXh5Np7k3n4G3Rixa0rf6Wa4wrWKtM4yUksmgpF17VwwgLHJvE8+tUacm9f99drlZQ8ahKMjRxQJAAUZCCxtLQUeMwGAs4W+H16goamscggcAWkf7hJqOT4p5ZJkhJL4sZ60E2xyDK9Viol/GsM3luU1Psx5kIBtgmQJANV3/ZsK9rI0RVGTjpIwPiAyww7ah5tl1wG1QPiG/R6wtSdjMwr3Kfs4+7vbMhdwZpNHSsJwcA4vRYBwMJ8Ug6w==";
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8080/UserManagement/user/add.do");
//		// -----------------------------
//		Map map = new TreeMap();
//		map.put("username", "王洪瑞");
//		map.put("password", "我家坐机号");
//		StringBuffer sb = new StringBuffer();
//		for (Object obj : map.entrySet()) {
//			sb.append(obj + "&");
//		}
//		String request = sb.substring(0, sb.length() - 1);
//		System.out.println(request);
//		// -------------------------------
//		// 用私钥签名
//		byte[] bytes = RSAUtils.encryptByPrivateKey(request.getBytes(), PRI_KEY);
//		String sign = RSAUtils.sign(bytes, PRI_KEY);
//		System.out.println(sign);
//		System.out.println(RSAUtils.verify(bytes, PUB_KEY, sign));
		// -----------------------------
		// 创建一个键值对的集合
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("username", "王洪瑞"));
		list.add(new BasicNameValuePair("password", "我家坐机号"));
//		list.add(new BasicNameValuePair("sign", sign));
		
		// 创建一个UrlEncodedFormEntity
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, Consts.UTF_8);
		// 把实体添加给post请求
		post.setEntity(entity);
		CloseableHttpResponse response = httpclient.execute(post);
		try {
			// 获取响应码
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			// http的实体
			HttpEntity entitys = response.getEntity();
			String string = EntityUtils.toString(entitys);
			System.out.println(string);
		} finally {
			response.close();
		}
	}
}
