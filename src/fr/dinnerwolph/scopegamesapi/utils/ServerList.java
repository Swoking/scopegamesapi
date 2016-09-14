package fr.dinnerwolph.scopegamesapi.utils;

public enum ServerList {
    HUB("hub");
    
    private String server;

    private ServerList(String selectserver) {
        this.server = selectserver;
    }

    public String getServer() {
        return this.server;
    }
}