package com.kaibacorp.cep.service;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EnderecoService {

    @Value("${topic.endereco-cliente}")
    private String topicEnderecoCliente;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMsg(String msg){
        this.kafkaTemplate.send(topicEnderecoCliente,msg);
        log.info("Mensagem enviada para o topic_endereco_cliente: {}",msg);
    }
}
