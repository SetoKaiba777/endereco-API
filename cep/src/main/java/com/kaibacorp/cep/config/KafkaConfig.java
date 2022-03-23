package com.kaibacorp.cep.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${topic.endereco-cliente}")
    private String topicEnderecoCliente;

    @Bean
    public NewTopic adviceTopic() {
        return new NewTopic(topicEnderecoCliente, 3, (short) 1);
    }
}
