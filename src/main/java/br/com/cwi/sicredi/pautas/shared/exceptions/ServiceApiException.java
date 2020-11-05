package br.com.cwi.sicredi.pautas.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ServiceApiException extends RuntimeException {

    @Getter
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public ServiceApiException(String message) {
        super(message);
    }

    public ServiceApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
