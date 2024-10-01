package org.kus.simple_server.http;

public class HttpResponse {
	HttpStatusCode status;
	String body;
	
	public HttpStatusCode getStatus() {
		return status;
	}
	
	public void setStatus(HttpStatusCode status) {
		this.status = status;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
}
