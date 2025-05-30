package org.kus.simple_server.http;

public enum HttpHeader {
	ACCEPT("Accept"),
	ACCEPT_CHARSET("Accept-Charset"),
	ACCEPT_ENCODING("Accept-Encoding"),
	ACCEPT_LANGUAGE("Accept-Language"),
	ACCEPT_PATCH("Accept-Patch"),
	ACCEPT_RANGES("Accept-Ranges"),
	ACCESS_CONTROL_ALLOW_CREDENTIALS("Access-Control-Allow-Credentials"),
	ACCESS_CONTROL_ALLOW_HEADERS("Access-Control-Allow-Headers"),
	ACCESS_CONTROL_ALLOW_METHODS("Access-Control-Allow-Methods"),
	ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),
	ACCESS_CONTROL_EXPOSE_HEADERS("Access-Control-Expose-Headers"),
	ACCESS_CONTROL_MAX_AGE("Access-Control-Max-Age"),
	ACCESS_CONTROL_REQUEST_HEADERS("Access-Control-Request-Headers"),
	ACCESS_CONTROL_REQUEST_METHOD("Access-Control-Request-Method"),
	AGE("Age"),
	ALLOW("Allow"),
	ALT_SVC("Alt-Svc"),
	AUTHORIZATION("Authorization"),
	CACHE_CONTROL("Cache-Control"),
	CLEAR_SITE_DATA("Clear-Site-Data"),
	CONNECTION("Connection"),
	CONTENT_DISPOSITION("Content-Disposition"),
	CONTENT_ENCODING("Content-Encoding"),
	CONTENT_LANGUAGE("Content-Language"),
	CONTENT_LENGTH("Content-Length"),
	CONTENT_LOCATION("Content-Location"),
	CONTENT_RANGE("Content-Range"),
	CONTENT_SECURITY_POLICY("Content-Security-Policy"),
	CONTENT_SECURITY_POLICY_REPORT_ONLY("Content-Security-Policy-Report-Only"),
	CONTENT_TYPE("Content-Type"),
	COOKIE("Cookie"),
	CROSS_ORIGIN_EMBEDDER_POLICY("Cross-Origin-Embedder-Policy"),
	CROSS_ORIGIN_OPENER_POLICY("Cross-Origin-Opener-Policy"),
	CROSS_ORIGIN_RESOURCE_POLICY("Cross-Origin-Resource-Policy"),
	DATE("Date"),
	DEVICE_MEMORY("Device-Memory"),
	DIGEST("Digest"),
	DNT("DNT"),
	DOWNLINK("Downlink"),
	EARLY_DATA("Early-Data"),
	ECT("ECT"),
	ETAG("ETag"),
	EXPECT("Expect"),
	EXPECT_CT("Expect-CT"),
	EXPIRES("Expires"),
	FEATURE_POLICY("Feature-Policy"),
	FORWARDED("Forwarded"),
	FROM("From"),
	HOST("Host"),
	IF_MATCH("If-Match"),
	IF_MODIFIED_SINCE("If-Modified-Since"),
	IF_NONE_MATCH("If-None-Match"),
	IF_RANGE("If-Range"),
	IF_UNMODIFIED_SINCE("If-Unmodified-Since"),
	KEEP_ALIVE("Keep-Alive"),
	LARGE_ALLOCATION("Large-Allocation"),
	LAST_MODIFIED("Last-Modified"),
	LINK("Link"),
	LOCATION("Location"),
	MAX_FORWARDS("Max-Forwards"),
	NEL("NEL"),
	ORIGIN("Origin"),
	PERMISSIONS_POLICY("Permissions-Policy"),
	PRAGMA("Pragma"),
	PROXY_AUTHENTICATE("Proxy-Authenticate"),
	PROXY_AUTHORIZATION("Proxy-Authorization"),
	PUBLIC_KEY_PINS("Public-Key-Pins"),
	PUBLIC_KEY_PINS_REPORT_ONLY("Public-Key-Pins-Report-Only"),
	RANGE("Range"),
	REFERER("Referer"),
	REFERRER_POLICY("Referrer-Policy"),
	REFRESH("Refresh"),
	REPORT_TO("Report-To"),
	RETRY_AFTER("Retry-After"),
	RTT("RTT"),
	SAVE_DATA("Save-Data"),
	SEC_CH_UA("Sec-CH-UA"),
	SEC_CH_UA_ARCH("Sec-CH-UA-Arch"),
	SEC_CH_UA_BITNESS("Sec-CH-UA-Bitness"),
	SEC_CH_UA_FULL_VERSION("Sec-CH-UA-Full-Version"),
	SEC_CH_UA_MOBILE("Sec-CH-UA-Mobile"),
	SEC_CH_UA_MODEL("Sec-CH-UA-Model"),
	SEC_CH_UA_PLATFORM("Sec-CH-UA-Platform"),
	SEC_FETCH_DEST("Sec-Fetch-Dest"),
	SEC_FETCH_MODE("Sec-Fetch-Mode"),
	SEC_FETCH_SITE("Sec-Fetch-Site"),
	SEC_FETCH_USER("Sec-Fetch-User"),
	SEC_WEBSOCKET_ACCEPT("Sec-WebSocket-Accept"),
	SERVER("Server"),
	SERVER_TIMING("Server-Timing"),
	SERVICE_WORKER_NAVIGATION_PRELOAD("Service-Worker-Navigation-Preload"),
	SET_COOKIE("Set-Cookie"),
	SOURCEMAP("SourceMap"),
	STRICT_TRANSPORT_SECURITY("Strict-Transport-Security"),
	TE("TE"),
	TIMING_ALLOW_ORIGIN("Timing-Allow-Origin"),
	TK("Tk"),
	TRAILER("Trailer"),
	TRANSFER_ENCODING("Transfer-Encoding"),
	UNDEFINED("UNDEFINED"),
	UPGRADE("Upgrade"),
	UPGRADE_INSECURE_REQUESTS("Upgrade-Insecure-Requests"),
	USER_AGENT("User-Agent"),
	VARY("Vary"),
	VIA("Via"),
	VIEWPORT_WIDTH("Viewport-Width"),
	WARNING("Warning"),
	WIDTH("Width"),
	WWW_AUTHENTICATE("WWW-Authenticate"),
	X_CONTENT_TYPE_OPTIONS("X-Content-Type-Options"),
	X_DNS_PREFETCH_CONTROL("X-DNS-Prefetch-Control"),
	X_FORWARDED_FOR("X-Forwarded-For"),
	X_FRAME_OPTIONS("X-Frame-Options"),
	X_POWERED_BY("X-Powered-By"),
	X_REQUESTED_WITH("X-Requested-With"),
	X_UA_COMPATIBLE("X-UA-Compatible"),
	X_XSS_PROTECTION("X-XSS-Protection");
	
	private String key;
	
	HttpHeader(String key) {
		this.key = key;
	}
	
	public static HttpHeader fromString(String text) {
        for (HttpHeader header : HttpHeader.values()) {
            if (header.key.equalsIgnoreCase(text)) {
                return header;
            }
        }
        return UNDEFINED;
    }
	
	@Override
	public String toString() {
		return this.key;
	}
	
	public String getKey() {
		return this.key;
	}
}
