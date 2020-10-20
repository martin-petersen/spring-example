package com.example.demo.model;


import com.example.demo.dto.PessoaDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String nome;
    private LocalDate dataNascimento;
    @NotBlank
    private String estado;
    private int idade;
    @NotNull
    private LocalDateTime dateCeated;

    public Pessoa() {
    }
    /*
     * Lembrando de ajustar o construtor para iniciar o nosso novo parâmetro, já que ele nunca pode ser nulo
     */
    public Pessoa(PessoaDTO pessoa) {
        this.nome = pessoa.getNome().toUpperCase();
        this.dataNascimento = pessoa.getDataNascimento();
        this.estado = pessoa.getEstado().toUpperCase();
        this.idade = pessoa.getIdade();
        this.dateCeated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public LocalDateTime getDateCeated() {
        return dateCeated;
    }

    public void setDateCeated(LocalDateTime dateCeated) {
        this.dateCeated = dateCeated;
    }
}


