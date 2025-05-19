package com.challenge.transactionalapi.adapter.in.web.dto.response;

public class ApiResponse<T> {
		
    private String message;
    private T content;

    public ApiResponse(String message, T content) {
        this.message = message;
        this.content = content;
    }
    
    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
