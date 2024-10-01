package org.kus.simple_server;

import static org.kus.simple_server.RequestParser.CRLF;

import java.io.IOException;
import java.net.URISyntaxException;

import org.kus.simple_server.http.HttpResponse;
import org.kus.simple_server.http.HttpStatusCode;

public class ResponseCreator {
	private final ContentSeeker contentSeeker;
	
	ResponseCreator() throws URISyntaxException {
		contentSeeker = new ContentSeeker();
	}
	
	public HttpResponse create(String path) throws IOException, URISyntaxException {
		HttpResponse response = new HttpResponse();
		String resource = contentSeeker.findResource(path);
		HttpStatusCode statusCode = defineStatus(resource);
		response.setStatus(statusCode);
		
		String payload = 
				buildStatusLine(statusCode) // Status Line
				+ "Content-Length: " + ((resource != null) ? String.valueOf(resource.getBytes().length) : "0") // Headers
				+ CRLF + CRLF;
		
		if (resource != null) {
			payload += 
					resource
					+ CRLF + CRLF; 
		}
		response.setBody(payload);
		
		return response;
	}
	
	private String buildStatusLine(HttpStatusCode statusCode) {
		return "HTTP/1.1 " + statusCode.getCode() + " " + statusCode.getMessage() + CRLF;
	}
	
	private HttpStatusCode defineStatus(String body) {
		if (body == null) {
			return HttpStatusCode.NOT_FOUND;
		} else {
			return  HttpStatusCode.OK;
		}
	}
}
