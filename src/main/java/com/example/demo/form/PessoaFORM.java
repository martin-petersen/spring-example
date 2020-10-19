package com.example.demo.form;

import java.time.LocalDate;

public class PessoaFORM {
    private String nome;
    private LocalDate dataNascimento;
    private String estado;
    private int idade;

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEstado() {
        return estado;
    }

    public int getIdade() {
        return idade;
    }
}
