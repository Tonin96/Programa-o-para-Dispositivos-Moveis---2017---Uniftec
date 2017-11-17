package br.com.uniftec.ecommercemobile.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

public class EcommerceResponse<T> {

    private EcommerceResponseStatus status;
    private String message;
    @JsonInclude(NON_NULL)
    private T data;

    public void setStatus(EcommerceResponseStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public EcommerceResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
