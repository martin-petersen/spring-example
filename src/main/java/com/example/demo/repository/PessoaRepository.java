package com.example.demo.repository;

import com.example.demo.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    /*
    * Aqui temos uma particularidade a notação @Query que é utilizada para
    * fazer modificações na função padrão criada pela JpaRepository, nesse caso
    * eu quero pegar buscar no banco todas as pessoas que tenham no nome o trecho
    * que foi passado no parâmetro do método.
    */
    @Query("from Pessoa i where upper(i.nome) like :nome")
    public List<Pessoa> findByNome(String nome);
    public List<Pessoa> findByEstado(String estado);
    public List<Pessoa> findByDataNascimentoAfter(LocalDate localDate);
    public List<Pessoa> findByDataNascimentoBefore(LocalDate localDate);
    public List<Pessoa> findByIdade(int idade);
}
