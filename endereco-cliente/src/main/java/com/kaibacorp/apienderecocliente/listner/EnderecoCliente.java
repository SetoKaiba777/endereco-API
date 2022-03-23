package com.kaibacorp.apienderecocliente.listner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaibacorp.apienderecocliente.entity.Endereco;
import com.kaibacorp.apienderecocliente.repository.EnderecoRepository;
import com.kaibacorp.apienderecocliente.service.EnderecoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EnderecoCliente {

    @Autowired
    private EnderecoService enderecoService;

    @KafkaListener(topics = "${topic.endereco-cliente}", groupId = "${spring.kafka.consumer.group-id}")
    public void obterEnderecoCliente(String enderecoString) throws JsonProcessingException{

        log.info("Mensagem endereço: {}",enderecoString);

        ObjectMapper objectMapper = new ObjectMapper();
        Endereco endereco = objectMapper.readValue(enderecoString,Endereco.class);
        var savedEndereco = enderecoService.save(endereco);
        log.info("Endereco Salvo com sucesso: {}",savedEndereco);
    }
}
