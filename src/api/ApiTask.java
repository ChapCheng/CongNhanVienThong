package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;
import webservice.WebProtocol;

public class ApiTask implements WebProtocol {
	protected static final HttpClient httpClient;
	protected String url;
	protected Map<String, String> params;
	protected String method;
	protected Exception lastException;
	public String result;
	public String erros;
	public String mess;
	public Context context;
	public int totalSize = 0;

	public ApiTask() {
		// TODO Auto-generated constructor stub
		this.url = null;
		this.params = new HashMap<String, String>();
		this.method = "GET";
		result = "";
	}

	static {
		httpClient = new DefaultHttpClient();
	}

	public void addParam(String name, String value) {
		this.params.put(name, value);
	}

	protected HttpGet createHttpGet() throws Exception {
		StringBuilder sb = new StringBuilder(this.url);
		String paramString = URLEncodedUtils.format(this.getParams(), "utf-8");
		if (url.contains("?")) {
			if (url.endsWith("?")) {
				sb.append(paramString);
			} else {
				sb.append("&").append(paramString);
			}
		} else {
			sb.append("?").append(paramString);
		}

		return new HttpGet(sb.toString());
	}

	protected HttpPost createHttpPost() throws Exception {
		HttpPost post = new HttpPost(this.url);
		post.setEntity(new UrlEncodedFormEntity(this.getParams(), HTTP.UTF_8));
		return post;
	}

	protected List<NameValuePair> getParams() {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : this.params.entrySet()) {
			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return pairs;
	}

	@Override
	public void execute() {
		try {
			HttpUriRequest request = this.createHttpGet();
			HttpResponse response = httpClient.execute(request);
			BufferedReader in = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			StringBuilder builder = new StringBuilder();
			String output = null;
			while ((output = in.readLine()) != null) {
				builder.append(output);
			}
			result = builder.toString();
			Log.d("DEBUG", builder.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub

		return null;
	}

	public <T> List<T> getList(final Class<T> clazz) {

		ArrayList<T> lstObject = new ArrayList<T>();
		try {
			JSONObject jsonRes = new JSONObject(result);
			JSONArray jsonArray = jsonRes.getJSONArray("Data");
			mess = jsonRes.getString("Message");
			erros = jsonRes.getString("IsError");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Gson gson = new Gson();
				T t = (T) gson.fromJson(jsonObject.toString(), clazz);
				lstObject.add(t);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstObject;
	}

	public <T> List<T> getListTotalSize(final Class<T> clazz, String name) {

		ArrayList<T> lstObject = new ArrayList<T>();
		try {
			JSONObject jsonRes = new JSONObject(result);
			JSONObject jos = jsonRes.getJSONObject("Data");
			JSONArray jsonArray = jos.getJSONArray(name);
			mess = jsonRes.getString("Message");
			erros = jsonRes.getString("IsError");
			totalSize = jos.getInt("TotalSize");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Gson gson = new Gson();
				T t = (T) gson.fromJson(jsonObject.toString(), clazz);
				lstObject.add(t);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstObject;
	}

	@Override
	public Void SetConText(Context context) {
		// TODO Auto-generated method stub
		this.context = context;
		return null;
	}

}
