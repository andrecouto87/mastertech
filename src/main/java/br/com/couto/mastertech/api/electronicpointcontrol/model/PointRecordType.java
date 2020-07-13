package br.com.couto.mastertech.api.electronicpointcontrol.model;

public enum PointRecordType {
    INPUT("IN"),
    OUTPUT("OUT");

    private String type;

    PointRecordType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}