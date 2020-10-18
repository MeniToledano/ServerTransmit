package com.meni.server.exception;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(String s) {
        System.out.println("Route does not exist!");
    }
}
