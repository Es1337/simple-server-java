package org.kus.simple_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

import org.kus.simple_server.http.HttpRequest;
import org.kus.simple_server.http.HttpHeader;

public class RequestParser {
	public static final String CRLF = "\n\r";
	private static final byte SP = 0x20;
	private static final byte CR = 0x0D;
	private static final byte LF = 0x0A;
	
	public HttpRequest parse(InputStream input) throws IOException {
		InputStreamReader reader = new InputStreamReader(input);
		HttpRequest request = new HttpRequest();
		request.setHeaders(new TreeMap<HttpHeader, String>());
		
		parseRequestLine(reader, request);
		parseHeaders(reader, request);
		parseBody(reader, request);
		
		return request;
	}

	private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException {
		int requestByte;
		StringBuilder buffer = new StringBuilder();
		
		while ((requestByte = reader.read()) >= 0) {
			if (requestByte == CR) {
				requestByte = reader.read();
				if (requestByte == LF) {
					// End of line attributes
					if (request.getVersion() == null) {
						String versionStripped = buffer.substring(buffer.lastIndexOf("/") + 1);
						request.setVersion(versionStripped);
						buffer.delete(0, buffer.length());
					}
					return;
				}
			}
			
			if (requestByte == SP) {
				// Read from buffer on space detected
				if (request.getMethod() == null) {
					request.setMethod(buffer.toString());
					buffer.delete(0, buffer.length());
				} else if (request.getPath() == null) {
					request.setPath(buffer.toString());
					buffer.delete(0, buffer.length());
				}
			} else {
				// Extend buffer
				buffer.append((char) requestByte);
			}
		}
		
	}

	private void parseHeaders(InputStreamReader reader, HttpRequest request) throws IOException {
		int requestByte;
		StringBuilder buffer = new StringBuilder();
		
		while ((requestByte = reader.read()) >= 0) {
			buffer.append((char) requestByte);
			
			if (requestByte == CR) {
				requestByte = reader.read();
				if (requestByte == LF) {
					if (buffer.toString().equals("" + (char)CR)) {
						break;
					}
					
					Map<HttpHeader, String> headers = request.getHeaders();
					HttpHeader key = HttpHeader.fromString(
							buffer.substring(0, buffer.lastIndexOf(":")).trim());
					String value = buffer.substring(buffer.lastIndexOf(":") + 1).trim();
					headers.put(key, value);
					
					buffer.delete(0, buffer.length());
				}
			}
		}
	}

	private void parseBody(InputStreamReader reader, HttpRequest request) throws IOException {
		int requestByte;
		StringBuilder buffer = new StringBuilder();
		
		while ((requestByte = reader.read()) >= 0) {
			buffer.append((char) requestByte);
		}
		
		request.setBody(buffer.toString());
	}
}
