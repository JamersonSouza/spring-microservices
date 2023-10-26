package tech.jamersondev.microservicecartoes.services;

import org.springframework.stereotype.Service;
import tech.jamersondev.microservicecartoes.domain.ClientCartao;
import tech.jamersondev.microservicecartoes.repositorys.ClientCartaoRepository;

import java.util.List;

@Service
public class ClientCartaoService {

    private final ClientCartaoRepository cartaoRepository;

    public ClientCartaoService(ClientCartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public List<ClientCartao>  listClientByCpf(String cpf){
        return cartaoRepository.findByCpf(cpf);
    }
}
