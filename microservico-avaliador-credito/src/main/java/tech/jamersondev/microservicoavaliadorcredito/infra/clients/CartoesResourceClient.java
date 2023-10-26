package tech.jamersondev.microservicoavaliadorcredito.infra.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.jamersondev.microservicoavaliadorcredito.domain.DTO.CartoesClienteDTO;
import tech.jamersondev.microservicoavaliadorcredito.domain.DTO.DadosCartaoClienteDTO;

import java.util.List;

@FeignClient(value = "microservice-cartoes", path = "cartao")
public interface CartoesResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<List<CartoesClienteDTO>> getCartoesByCpf(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    public ResponseEntity<List<DadosCartaoClienteDTO>> getCartoesByRenda(@RequestParam("renda") Long renda);


}
