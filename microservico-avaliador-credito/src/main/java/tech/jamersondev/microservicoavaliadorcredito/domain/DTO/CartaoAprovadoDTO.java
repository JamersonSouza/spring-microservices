package tech.jamersondev.microservicoavaliadorcredito.domain.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartaoAprovadoDTO {

    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;

}
