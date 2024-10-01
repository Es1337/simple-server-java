package org.kus.simple_server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContentSeekerTest {
	private ContentSeeker contentSeeker;
	
	@BeforeAll
	public void setUp() throws URISyntaxException {
		contentSeeker = new ContentSeeker();
	}
	
	@Test
	public void shouldNotFindResource() throws IOException, URISyntaxException {
		// given
		String path = "test/path/not/to/file/index.html";
		
		// when
		String payload = contentSeeker.findResource(path);
		
		// then
		assertEquals(null, payload);
	}
	
	@Test
	public void shouldFindResource() throws IOException, URISyntaxException {
		// given
		String path = "/test/path/to/file/index.html";
		String expected = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>Page Title</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>This is a Heading</h1>"
				+ "<p>This is a paragraph.</p>"
				+ "</body>"
				+ "</html>";
		
		// when
		String payload = contentSeeker.findResource(path);
		
		// then
		assertEquals(expected, payload);
	}
}
