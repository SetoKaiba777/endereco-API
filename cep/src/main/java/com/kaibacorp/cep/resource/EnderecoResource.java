package com.kaibacorp.cep.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaibacorp.cep.entity.EnderecoJson;
import com.kaibacorp.cep.responseEntity.EnderecoResponse;
import com.kaibacorp.cep.service.EnderecoService;
import com.kaibacorp.cep.service.ViaCepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("endereco")
public class EnderecoResource {

    private final ViaCepService viaCepService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponse> obterCep(@PathVariable("cep") String cep){
        EnderecoResponse endereco = viaCepService.obterCep(cep);
        return ResponseEntity.ok(endereco);
    }

    @PostMapping
    public ResponseEntity enviarEndereco(@RequestBody EnderecoJson enderecoJson) throws JsonProcessingException{
        log.info("## dados enviados pelo cliente: {}", enderecoJson);

        EnderecoResponse enderecoResponse = viaCepService.obterCep(enderecoJson.getCep());

        enderecoResponse.setComplemento(enderecoJson.getComplemento());
        enderecoResponse.setNumero(enderecoJson.getNumero());

        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(enderecoResponse);

        enderecoService.sendMsg(msg);
        log.info("## Endereço Retornado pela API de CEP: {}", enderecoResponse);

        return  ResponseEntity.ok(enderecoResponse);

    }
}
