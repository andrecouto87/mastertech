package br.com.couto.mastertech.model;

public enum TipoBatidaModel {
    INPUT("IN"),
    OUTPUT("OUT");

    private String type;

    TipoBatidaModel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}