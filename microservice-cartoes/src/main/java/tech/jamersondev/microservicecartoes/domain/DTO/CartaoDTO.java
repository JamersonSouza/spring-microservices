package tech.jamersondev.microservicecartoes.domain.DTO;

import lombok.Getter;
import lombok.Setter;
import tech.jamersondev.microservicecartoes.domain.Cartao;
import tech.jamersondev.microservicecartoes.domain.enums.BandeiraCartao;

import java.math.BigDecimal;

@Getter
@Setter
public class CartaoDTO {
    private String nome;
    private BandeiraCartao bandeiraCartao;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao toModel(){
        return new Cartao(nome, bandeiraCartao, renda, limiteBasico);
    }
}
