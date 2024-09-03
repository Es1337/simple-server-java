package org.kus.simple_server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.kus.simple_server.http.HttpRequest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RequestParserTest {
	private RequestParser requestParser;
	
	@BeforeAll
	public void setUp() {
		requestParser = new RequestParser();
	}
	
	@Test
	public void shouldParseRequest() throws IOException {
		String input = "GET / HTTP/1.1\r\n"
				+ "Content-Type: application/json\r\n"
				+ "Content-Length: 69\r\n"
				+ "\r\n"
				+ "CHUJDUPACYCKI\r\n";
		
		InputStream testInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		
		HttpRequest request = requestParser.parse(testInput);
		
		assertEquals(request.getMethod(), "GET");
		assertEquals(request.getPath(), "/");
		assertEquals(request.getVersion(), "1.1");
		assertEquals(request.getHeaders(), Map.of(
				"Content-Type", "application/json",
				"Content-Length", "69"));
		assertEquals(request.getBody(), "CHUJDUPACYCKI\r\n");
	}
}
