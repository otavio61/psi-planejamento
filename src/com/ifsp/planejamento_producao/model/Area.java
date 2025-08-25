package com.ifsp.planejamento_producao.model;

public class Area {
    private int id;
    private String nome, tipoCultura;
    private double ph;

    public Area(
        int id, String nome, 
        String tipoCultura,
        double ph
    ) {
        this.id = id;
        this.nome = nome;
        this.tipoCultura = tipoCultura;
        this.ph = ph;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPh() {
        return ph;
    }
    public void setPh(double ph) {
        this.ph = ph;
    }

    public String getTipoCultura() {
        return tipoCultura;
    }
    public void setTipoCultura(String tipoCultura) {
        this.tipoCultura = tipoCultura;
    }
}
