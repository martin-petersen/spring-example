package com.example.demo.service;

import com.example.demo.dto.PessoaResponseResponseDTO;
import com.example.demo.dto.PessoaDTO;
import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public PessoaResponseResponseDTO save(PessoaDTO pessoa) {
        Pessoa novaPessoa = new Pessoa(pessoa);
        pessoaRepository.save(novaPessoa);
        return new PessoaResponseResponseDTO(novaPessoa.getId(),novaPessoa.getNome(),novaPessoa.getEstado(),novaPessoa.getDateCeated());
    }

    @Transactional
    public PessoaResponseResponseDTO update(Long id, PessoaDTO pessoa) {
        if(pessoaRepository.findById(id).isPresent()) {
            Pessoa attPessoa = pessoaRepository.findById(id).get();
            attPessoa.setNome(pessoa.getNome().toUpperCase());
            attPessoa.setEstado(pessoa.getEstado().toUpperCase());
            attPessoa.setDataNascimento(pessoa.getDataNascimento());
            attPessoa.setIdade(pessoa.getIdade());
            pessoaRepository.save(attPessoa);
            return new PessoaResponseResponseDTO(attPessoa.getId(),attPessoa.getNome(),attPessoa.getEstado(),attPessoa.getDateCeated());
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

    public List<PessoaResponseResponseDTO> findByNome(String nome) {
        return conversorEntidadeParaDTO(pessoaRepository.findByNome(nome.toUpperCase()));
    }

    public List<PessoaResponseResponseDTO> findByUF(String naturalidade) {
        return conversorEntidadeParaDTO(pessoaRepository.findByEstado(naturalidade.toUpperCase()));
    }

    public List<PessoaResponseResponseDTO> findByDataNascimentoAfter(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dataNascimento,formatter);
        return conversorEntidadeParaDTO(pessoaRepository.findByDataNascimentoAfter(localDate));
    }

    public List<PessoaResponseResponseDTO> findByDataNascimentoBefore(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dataNascimento,formatter);
        return conversorEntidadeParaDTO(pessoaRepository.findByDataNascimentoBefore(localDate));
    }

    public List<PessoaResponseResponseDTO> findByIdade(int idade) {
        return conversorEntidadeParaDTO(pessoaRepository.findByIdade(idade));
    }

    public List<PessoaResponseResponseDTO> findAll() {
        return conversorEntidadeParaDTO(pessoaRepository.findAll());
    }

    /*
     * Nosso método com função de converter as listas de objetos, criado utilizando
     * streams presente a partir do Java 8, mas se não tiver domínio podem utilizar
     * for ou foreach sem qualquer prejuízo.
     */

    private List<PessoaResponseResponseDTO> conversorEntidadeParaDTO(List<Pessoa> pessoas) {
        List<PessoaResponseResponseDTO> pessoasDTO = new ArrayList<>();
        pessoasDTO = pessoas.stream().map(pessoa -> new PessoaResponseResponseDTO(pessoa.getId(),
                pessoa.getNome(),pessoa.getEstado(),pessoa.getDateCeated())).collect(Collectors.toList());
        return pessoasDTO;
    }
}
