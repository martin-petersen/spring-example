package com.example.demo.dto;

import com.example.demo.model.Pessoa;

import java.time.LocalDateTime;

public class PessoaDTO {
    private Long id;
    private String nome;
    private String estado;
    private LocalDateTime dateCreated;

    public PessoaDTO() {
    }

    public PessoaDTO(Long id, String nome, String estado, LocalDateTime dateCreated) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.dateCreated = dateCreated;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
