package org.kus.simple_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kus.simple_server.RequestParser.CRLF;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.kus.simple_server.http.HttpResponse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResponseCreatorTest {
	private ResponseCreator responseCreator;
	
	@BeforeAll
	public void setUp() throws URISyntaxException {
		responseCreator = new ResponseCreator();
	}
	
	@Test
	public void shouldCreateOkResponse() throws IOException, URISyntaxException {
		// given
		String path = "test/path/to/file/index.html";
		String expectedBody =  "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>Page Title</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>This is a Heading</h1>"
				+ "<p>This is a paragraph.</p>"
				+ "</body>"
				+ "</html>";
		String expectedResponse = 
				"HTTP/1.1 200 OK" + CRLF // Status Line
				+ "Content-Length: " + expectedBody.getBytes().length // Headers
				+ CRLF + CRLF
				+ expectedBody
				+ CRLF + CRLF; 
		
		// when
		HttpResponse response = responseCreator.create(path);
		
		// then
		assertEquals(expectedResponse, response.getBody());
	}
	
	@Test
	public void shouldNotCreateResponse() throws IOException, URISyntaxException {
		// given
				String path = "test/path/not/to/file/index.html";
				String expectedResponse = 
						"HTTP/1.1 404 Not Found" + CRLF // Status Line
						+ "Content-Length: " + 0 // Headers
						+ CRLF + CRLF; 
				
				// when
				HttpResponse response = responseCreator.create(path);
				
				// then
				assertEquals(expectedResponse, response.getBody());
	}
}
