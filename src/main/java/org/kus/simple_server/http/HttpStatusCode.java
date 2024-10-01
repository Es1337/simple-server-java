package org.kus.simple_server.http;

public enum HttpStatusCode {
	OK(200, "OK"),
	BAD_REQUEST(400, "Bad Request"),
	NOT_FOUND(404, "Not Found");
	
	private int code;
	private String message;
	
	HttpStatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}
