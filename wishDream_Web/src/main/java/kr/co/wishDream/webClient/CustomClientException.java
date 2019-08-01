package kr.co.wishDream.webClient;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientException;

public class CustomClientException extends WebClientException {

	private static final long serialVersionUID = 1L;
	
	private final HttpStatus status;
    private final ErrorDetails details;

	public CustomClientException(HttpStatus status, ErrorDetails errorDetails) {
		super(status.getReasonPhrase());
        this.status = status;
        this.details = errorDetails;
	}
	
	public HttpStatus getStatus() {
        return status;
    }

    public ErrorDetails getDetails() {
        return details;
    }

}
