package org.kus.simple_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;

import org.kus.simple_server.http.HttpRequest;

import static org.kus.simple_server.RequestParser.CRLF;

public class ServerApp {
	private static final int port = 8080;
	private static final Logger logger = new ConsoleLogger();
	
	public static void main(String[] args) {
		try (ServerSocket socket = new ServerSocket(port)) {
			logger.log("Server started.");
			while (true) {
				Socket client = socket.accept();
				
				InputStream input = client.getInputStream();
				OutputStream output = client.getOutputStream();
				RequestParser requestParser = new RequestParser();
				
				HttpRequest request = requestParser.parse(input);
				logger.log("Received request: <" + request.getMethod() + ", " + request.getPath() + ", " + request.getVersion() + ">");
				
				String body = "HelloWorld!";
				String response = 
						"HTTP/1.1 200 OK" + CRLF // Status Line
						+ "Content-Length: " + body.getBytes().length // Headers
						+ CRLF + CRLF
						+ body
						+ CRLF + CRLF; 
				
				logger.log("Sending response: " + response);
				output.write(response.getBytes());
				
				input.close();
				output.close();
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			logger.log("Server closed.");
		}
	}

}
