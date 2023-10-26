package tech.jamersondev.microservicoavaliadorcredito.domain.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CartoesClienteDTO {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;


}
