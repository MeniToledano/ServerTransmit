package com.meni.server.model;

public enum Status {

    RESOLVED("RESOLVED"),
    MATCH_FOUND("MATCH_FOUND"),
    PENDING("PENDING");

    private final String status;

    Status(String stat) {
        this.status = stat;
    }
}

