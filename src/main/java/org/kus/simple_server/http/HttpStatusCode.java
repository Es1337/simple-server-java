package org.kus.simple_server.http;

public enum HttpStatusCode {
	BAD_REQUEST(400, "Bad Request");
	
	private int code;
	private String message;
	
	HttpStatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
