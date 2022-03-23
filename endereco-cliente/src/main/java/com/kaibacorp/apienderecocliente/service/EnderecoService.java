package com.kaibacorp.apienderecocliente.service;

import com.kaibacorp.apienderecocliente.entity.Endereco;
import com.kaibacorp.apienderecocliente.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco save(Endereco endereco){
        return enderecoRepository.save(endereco);
    }
}
