package tech.jamersondev.microservicecartoes.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.jamersondev.microservicecartoes.domain.ClientCartao;

import java.util.List;
import java.util.UUID;

public interface ClientCartaoRepository extends JpaRepository<ClientCartao, UUID> {
    List<ClientCartao> findByCpf(String cpf);
}
