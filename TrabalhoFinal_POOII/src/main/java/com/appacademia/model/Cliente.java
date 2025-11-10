package com.appacademia.model;

import java.time.LocalDate;

public class Cliente extends Usuario{
    private int id_cliente;
    private String plano;
    private LocalDate data_inicio;
    private LocalDate data_vencimento;
    private boolean status;

    public Cliente(){}

    public Cliente(int id, String plano, LocalDate data_inicio, LocalDate data_vencimento, boolean status) {
        this.id_cliente = id;
        this.plano = plano;
        this.data_inicio = data_inicio;
        this.data_vencimento = data_vencimento;
        this.status = status;
    }


    public int getId_cliente() {return id_cliente;}
    public void setId_cliente(int id) {this.id_cliente = id;}

    public String getPlano() {return plano;}

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(LocalDate data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
