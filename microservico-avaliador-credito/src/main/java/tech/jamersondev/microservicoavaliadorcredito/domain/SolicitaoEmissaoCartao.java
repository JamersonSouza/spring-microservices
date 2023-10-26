package tech.jamersondev.microservicoavaliadorcredito.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class SolicitaoEmissaoCartao {

    private UUID cartaoIdentifier;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
