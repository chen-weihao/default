package urldemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class UrlTest {

	@Test
	public void testURLConnection() throws IOException {
		//设置URL
		URL hello = new URL("http://localhost:8080/");
		//创建Connection
		HttpURLConnection connection = (HttpURLConnection) hello.openConnection();
		connection.setRequestMethod("GET");
		//开启连接
		connection.connect();
		//获取响应状态
		int code = connection.getResponseCode();
		//解析响应数据
		if (code == HttpURLConnection.HTTP_OK) {
			InputStream in = connection.getInputStream();
			System.out.println(readLine(in));
		}
	}
	
	@Test
	public void testURLConnection2() throws IOException {
		URL url = new URL("http://101.132.175.96:8080/listByPage");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setDoOutput(true);
		conn.connect();
		
		OutputStream out = conn.getOutputStream();
		out.write("page=1&size=5".getBytes());
		
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream in = conn.getInputStream();
			System.out.println(readLine(in));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String readLine(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		return br.readLine();
	}
	
}
