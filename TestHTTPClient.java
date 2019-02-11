package com.sap.jira.plugins.constant;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;

import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestHTTPClient {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String userName = "TT_IDT";

		String password = "Password123";

		String url = "https://sapjira-test.wdf.sap.corp/rest/api/latest/issue/ARC-1";

		String userPassword = userName + ":" + password;

		String encodeBase64 = Base64.getEncoder().encodeToString(userPassword.getBytes("utf-8"));

		HttpGet request = new HttpGet(url);

		String authHeader = "Basic " + encodeBase64;
		request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(request);

		HttpEntity entity = response.getEntity();

		StringWriter writer = new StringWriter();
		IOUtils.copy(entity.getContent(), writer);
		System.out.println(writer.toString());
		
		System.out.println(response.getStatusLine().getStatusCode());

	}
}
