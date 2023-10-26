package tech.jamersondev.microservicoavaliadorcredito.domain.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class DadosCartaoClienteDTO {

    private UUID cartaoIdentifier;
    private String nome;
    private String bandeiraCartao;
    private BigDecimal limiteBasico;


}
