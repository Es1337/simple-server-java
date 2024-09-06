package org.kus.simple_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kus.simple_server.http.HttpHeader.CONTENT_LENGTH;
import static org.kus.simple_server.http.HttpHeader.CONTENT_TYPE;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.assertj.core.api.Assertions;
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
		String body = "TESTBODYTESTBODYTESTBODY\n"
				+ "TESTBODYTESTBODYTESTBODY"
				+ "TESTBODYTESTBODY"
				+ "TESTBODY\r\n";
		String input = 
				"GET / HTTP/1.1\r\n"
				+ "Content-Type: application/json\r\n"
				+ "Content-Length: " + body.length() + "\r\n"
				+ "\r\n"
				+ body;

		InputStream testInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

		HttpRequest request = requestParser.parse(testInput);

		assertEquals(request.getMethod(), "GET");
		assertEquals(request.getPath(), "/");
		assertEquals(request.getVersion(), "1.1");
		Assertions.assertThat(request.getHeaders())
				.containsAllEntriesOf(Map.of(
						CONTENT_LENGTH, Integer.toString(body.length()), 
						CONTENT_TYPE, "application/json"));
		assertEquals(request.getBody(), 
				"TESTBODYTESTBODYTESTBODY\n"
				+ "TESTBODYTESTBODYTESTBODY"
				+ "TESTBODYTESTBODY"
				+ "TESTBODY\r\n");
	}
}
