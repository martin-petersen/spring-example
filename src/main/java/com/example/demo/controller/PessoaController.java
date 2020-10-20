package com.example.demo.controller;

import com.example.demo.dto.PessoaResponseResponseDTO;
import com.example.demo.dto.PessoaDTO;
import com.example.demo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    /*
     * Essa notação indica que esse método vai ser chamado em requisições GET
     */
    @GetMapping
    public ResponseEntity<List<PessoaResponseResponseDTO>> listagemPessoas(@RequestParam(required = false)String nome,
                                                                           @RequestParam(required = false)String uf,
                                                                           @RequestParam(required = false)String depois,
                                                                           @RequestParam(required = false)String antes,
                                                                           @RequestParam(required = false)Integer idade) {
        if(nome!=null) {
            return ResponseEntity.ok(pessoaService.findByNome(nome.toUpperCase()));
        } else if(uf!=null) {
            return ResponseEntity.ok(pessoaService.findByUF(uf.toUpperCase()));
        } else if(idade!=null) {
            return ResponseEntity.ok(pessoaService.findByIdade(idade));
        } else if(depois!=null) {
            return ResponseEntity.ok(pessoaService.findByDataNascimentoAfter(depois));
        } else if(antes!=null) {
            return ResponseEntity.ok(pessoaService.findByDataNascimentoBefore(antes));
        } else {
            return ResponseEntity.ok(pessoaService.findAll());
        }
    }

    /*
     * Essa notação indica que esse método vai ser chamado em requisições POST
     */
    @PostMapping
    public ResponseEntity<PessoaResponseResponseDTO> salvar(@Valid @RequestBody PessoaDTO p,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        PessoaResponseResponseDTO pessoa = pessoaService.save(p);
        URI uri = uriComponentsBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    /*
     * Essa notação indica que esse método vai ser chamado em requisições PUT
     */

    /*
     * As notações @PathVariable e @RequestBody significam respectivamente que esse controller
     * recebe uma variável que vem do caminho da URL por isso Path Variable e ele também
     * recebe no corpo da requisição, por isso Request Body, um objeto json do tipo Pessoa
     */
    @PutMapping("{id}")
    public ResponseEntity<PessoaResponseResponseDTO> atualizar(@PathVariable Long id,
                                                               @Valid @RequestBody PessoaDTO p) {
        PessoaResponseResponseDTO pessoa = pessoaService.update(id, p);
        return ResponseEntity.ok(pessoa);
    }

    /*
     * Essa notação indica que esse método vai ser chamado em requisições DELETE
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }
}