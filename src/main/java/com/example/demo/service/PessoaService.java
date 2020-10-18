package com.example.demo.service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    /*
    * A notação @Transactional cria uma transação com o banco de dados,
    * trocando em miúdos, em uma situação onde várias classes repository
    * estejam sendo utilizadas, se alguma transação com banco em um desses
    * repositorys falhar, então todos falham, mesmo que uma delas tenha sido
    * concluída com sucesso acontece um rollback. Ou todas as transações funcionam
    * ou nenhuma delas funciona. No nosso caso não é funcional pois só temos um
    * repository mas fica de aprendizado.
    */
    @Transactional
    public Pessoa save(Pessoa pessoa) {
        Pessoa novaPessoa = new Pessoa(pessoa);
        pessoaRepository.save(novaPessoa);
        return novaPessoa;
    }

    @Transactional
    public Pessoa update(Long id, Pessoa pessoa) {
        if(pessoaRepository.findById(id).isPresent()) {
            Pessoa attPessoa = pessoaRepository.findById(id).get();
            attPessoa.setNome(pessoa.getNome().toUpperCase());
            attPessoa.setEstado(pessoa.getEstado().toUpperCase());
            attPessoa.setDataNascimento(pessoa.getDataNascimento());
            attPessoa.setIdade(pessoa.getIdade());
            pessoaRepository.save(attPessoa);
            return attPessoa;
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(Long id) {
        if(pessoaRepository.findById(id).isPresent()) {
            pessoaRepository.delete(pessoaRepository.findById(id).get());
        }
    }

    public List<Pessoa> findByNome(String nome) {
        return pessoaRepository.findByNome(nome.toUpperCase());
    }

    public List<Pessoa> findByUF(String naturalidade) {
        return pessoaRepository.findByEstado(naturalidade.toUpperCase());
    }

    public List<Pessoa> findByDataNascimentoAfter(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dataNascimento,formatter);
        return pessoaRepository.findByDataNascimentoAfter(localDate);
    }

    public List<Pessoa> findByDataNascimentoBefore(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dataNascimento,formatter);
        return pessoaRepository.findByDataNascimentoBefore(localDate);
    }

    public List<Pessoa> findByIdade(int idade) {
        return pessoaRepository.findByIdade(idade);
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }
}
