package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class PessoaDTO {
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;
    private LocalDate dataNascimento;
    @NotBlank(message = "UF não pode ser vazio")
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
