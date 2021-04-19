package com.KudryashevDaniil.terminalServer;

public class Request {
    public Boolean status;
    public String data;

    public Request(Boolean status, String data) {
        this.status = status;
        this.data = data;
    }
}
