package tech.jamersondev.microservicoavaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.jamersondev.microservicoavaliadorcredito.domain.DTO.DadosClienteDTO;

@FeignClient(value = "microservico-client", path = "clients")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<DadosClienteDTO> getClient(@RequestParam("cpf") String cpf);

}
