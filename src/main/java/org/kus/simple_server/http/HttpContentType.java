package org.kus.simple_server.http;

public enum HttpContentType {
	APPLICATION_JSON("application/json"),
	UNKNOWN("unknown");
	
	public String value;
	
	HttpContentType(String value) {
		this.value = value;
	}
	
	public static HttpContentType fromString(String text) {
        for (HttpContentType type : HttpContentType.values()) {
            if (type.value.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
