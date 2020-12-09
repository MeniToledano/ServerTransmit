package com.meni.server.model;

public enum EStatus {

    RESOLVED("RESOLVED"),
    MATCH_FOUND("MATCH_FOUND"),
    PENDING("PENDING");

    private final String status;

    EStatus(String stat) {
        this.status = stat;
    }
}

