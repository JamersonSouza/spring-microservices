package tech.jamersondev.microservicecartoes.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jamersondev.microservicecartoes.domain.Cartao;
import tech.jamersondev.microservicecartoes.domain.ClientCartao;
import tech.jamersondev.microservicecartoes.domain.DTO.CartaoDTO;
import tech.jamersondev.microservicecartoes.domain.DTO.ClientCartaoDTO;
import tech.jamersondev.microservicecartoes.services.CartaoService;
import tech.jamersondev.microservicecartoes.services.ClientCartaoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartao")
@Slf4j
public class CartaoResource {

    private final CartaoService cartaoService;
    private final ClientCartaoService clientCartaoService;

    public CartaoResource(CartaoService cartaoService, ClientCartaoService clientCartaoService) {
        this.cartaoService = cartaoService;
        this.clientCartaoService = clientCartaoService;
    }

    @GetMapping("/test")
    public String testCartao(){
        log.info("Testando cartão");
        return "Teste cartão get";
    }

    @PostMapping
    public ResponseEntity cadastrarCartao(@RequestBody CartaoDTO cartaoDTO){
            Cartao cartao = cartaoDTO.toModel();
            cartaoService.saveCartao(cartao);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesByRenda(@RequestParam("renda") Long renda){
        List<Cartao> cartoesRenda = this.cartaoService.getCartoesRenda(renda);
        return ResponseEntity.ok(cartoesRenda);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClientCartaoDTO>> getCartoesByCpf(@RequestParam("cpf") String cpf) {
        List<ClientCartao> clientCartaos = this.clientCartaoService.listClientByCpf(cpf);
        List<ClientCartaoDTO> resultList = clientCartaos.stream()
                .map(ClientCartaoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

}
