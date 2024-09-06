package org.kus.simple_server.http;

import java.util.Map;
import java.util.TreeMap;

public class HttpRequest {
	private String method;
	private String path;
	private String version;
	private Map<HttpHeader, String> headers;
	private String body;
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public Map<HttpHeader, String> getHeaders() {
		return headers;
	}
	
	public void setHeaders(TreeMap<HttpHeader, String> treeMap) {
		this.headers = treeMap;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
}
