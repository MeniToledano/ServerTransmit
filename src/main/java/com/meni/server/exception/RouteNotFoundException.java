package com.meni.server.exception;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(String msg) {
        System.out.println(msg);
    }
}
