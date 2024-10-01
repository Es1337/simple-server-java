package org.kus.simple_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

import org.kus.simple_server.http.HttpRequest;
import org.kus.simple_server.http.HttpResponse;

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
				ResponseCreator responseCreator = new ResponseCreator();
				
				HttpRequest request = requestParser.parse(input);
				logger.log("Received request: <" + request.getMethod() + ", " + request.getPath() + ", " + request.getVersion() + ">");
				
				HttpResponse response = responseCreator.create(request.getPath());
				
				logger.log("Sending response: " + response);
				output.write(response.getBody().getBytes());
				
				input.close();
				output.close();
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {			
			logger.log("Server closed.");
		}
	}

}
