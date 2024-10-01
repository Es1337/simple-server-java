package org.kus.simple_server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ContentSeeker {
	private static final String CONTENT_DIR = "/content";
	private Path content;
	
	public ContentSeeker() throws URISyntaxException {
		this.content = Paths.get(getClass().getResource(CONTENT_DIR).toURI());
	}

	public String findResource(String resourcePath) throws IOException, URISyntaxException {
		String payload;
		try (Stream<Path> walk = Files.walk(content, 10)) {
			Path resourceFile = walk
					.filter(path -> path.equals(Paths.get(content.toString(), resourcePath)))
					.toList().get(0);
			
			payload = fileToString(new BufferedReader(new FileReader(resourceFile.toString())));
		} catch (IndexOutOfBoundsException e) {
			return null;
		} catch (FileNotFoundException e) {
			return null;
		}
		
		return payload;
	}
	
	private String fileToString(BufferedReader fileReader) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		String line;
		while ((line = fileReader.readLine()) != null) {
			builder.append(line);
		}
		
		return builder.toString();
	}
}
