package com.appacademia.model;

import java.time.LocalDate;

public class Funcionario extends Usuario{
    private int id_func;
    private String cargo;
    private float salario;
    private LocalDate data_admissao;
    private String senha;

    public Funcionario(){}

    public Funcionario(int id_func, String cargo, float salario, LocalDate data_admissao, String senha) {
        this.id_func = id_func;
        this.cargo = cargo;
        this.salario = salario;
        this.data_admissao = data_admissao;
        this.senha = senha;
    }

    public int getId_func() {
        return id_func;
    }

    public void setId_func(int id_func) {
        this.id_func = id_func;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public LocalDate getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(LocalDate data_admissao) {
        this.data_admissao = data_admissao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
