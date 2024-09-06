package org.kus.simple_server;

public class ConsoleLogger implements Logger {

	public void log(String text) {
		System.out.println(text);
	}

}
