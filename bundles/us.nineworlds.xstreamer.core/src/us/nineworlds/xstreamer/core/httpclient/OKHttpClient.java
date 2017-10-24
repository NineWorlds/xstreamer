package us.nineworlds.xstreamer.core.httpclient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OKHttpClient {

	private static OkHttpClient client;
	
	public static OkHttpClient getInstance() {
		if (client == null) {
			client = new OkHttpClient.Builder()
					.connectTimeout(20, TimeUnit.SECONDS)
					.readTimeout(20, TimeUnit.SECONDS)
					.followRedirects(true)
					.build();
		}
		return client;
	}
}
