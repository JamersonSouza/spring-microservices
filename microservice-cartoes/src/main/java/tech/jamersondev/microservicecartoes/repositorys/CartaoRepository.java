package tech.jamersondev.microservicecartoes.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.jamersondev.microservicecartoes.domain.Cartao;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CartaoRepository extends JpaRepository<Cartao, UUID> {
    List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
}
