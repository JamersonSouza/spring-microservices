package tech.jamersondev.microservicoavaliadorcredito.HandlerExceptions;

import lombok.Getter;

public class ErrorMicroServiceExceptions extends Exception{

    @Getter
    private Integer status;
    public ErrorMicroServiceExceptions(String msg, Integer stats) {
        super(msg);
        this.status = stats;
    }
}
