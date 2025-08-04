package com.mina.kartngo.data.util;

public abstract class Result<T> {
    public static class Success<T> extends Result<T> {
        private final T data;
        public Success(T data) { this.data = data; }
        public T getData() { return data; }
    }

    public static class Error<T> extends Result<T> {
        private final String message;
        public Error(String message) { this.message = message; }
        public String getMessage() { return message; }
    }
}

