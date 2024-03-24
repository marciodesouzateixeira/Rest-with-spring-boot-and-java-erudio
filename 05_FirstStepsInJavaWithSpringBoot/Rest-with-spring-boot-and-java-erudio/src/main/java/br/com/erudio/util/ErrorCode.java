package br.com.erudio.util;

public class ErrorCode {
    private String message;
    private int status;

    // Construtor
    public ErrorCode(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // Getters e setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

