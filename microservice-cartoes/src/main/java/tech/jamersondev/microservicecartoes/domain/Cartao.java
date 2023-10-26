package tech.jamersondev.microservicecartoes.domain;


import lombok.*;
import tech.jamersondev.microservicecartoes.domain.enums.BandeiraCartao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cartao {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID cartaoIdentifier;
    private String nome;
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeiraCartao;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao(String nome, BandeiraCartao bandeiraCartao, BigDecimal renda, BigDecimal limiteBasico) {
        this.nome = nome;
        this.bandeiraCartao = bandeiraCartao;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }
}
