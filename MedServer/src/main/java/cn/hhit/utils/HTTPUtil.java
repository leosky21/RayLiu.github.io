package cn.hhit.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HTTPUtil {
	public static HttpClient httpclient = new DefaultHttpClient();
	public static final String BASE_URL = "http://api.avatardata.cn/Cook/CookClass?key=1c4e00dbb2db47699ce6330d8f350f41&id=1";
	public static final String url = "http://api.avatardata.cn/Cook/CookClass";
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("key", "1c4e00dbb2db47699ce6330d8f350f41");
		map.put("id", "1");
		map.put("dtype", "");
		map.put("format", "true");
		try {
			String abc =HTTPUtil.postRequest(url, map);
			System.out.println(abc.toString());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		try {
//			String bc = HTTPUtil.getRequest(BASE_URL);
//			System.out.println(bc.toString());
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public static String getRequest(final String url) throws InterruptedException, ExecutionException {
		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				HttpGet get = new HttpGet(url);
				HttpResponse hresp = httpclient.execute(get);
				if (hresp.getStatusLine().getStatusCode() == 200) {
					String result = EntityUtils.toString(hresp.getEntity());
					return result;
				}
				return null;
			}

		});

		new Thread(task).start();
		return task.get();
	}

	public static String postRequest(final String url,final Map<String,String> rawParams) throws InterruptedException, ExecutionException{
		FutureTask<String> task = new FutureTask<String>(
				new Callable<String>() {

					@Override
					public String call() throws Exception {
						HttpPost post = new HttpPost(url);
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						for(String key : rawParams.keySet()){
							params.add(new BasicNameValuePair(key, rawParams.get(key)));
						}
						post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
						
						HttpResponse httpResponse = httpclient.execute(post);
						if(httpResponse.getStatusLine().getStatusCode() == 200){
							String result = EntityUtils.toString(httpResponse.getEntity());
							return result;
						};
						return null;
					}
				});
	
		new Thread(task).start();;
		return task.get();
	}
}
