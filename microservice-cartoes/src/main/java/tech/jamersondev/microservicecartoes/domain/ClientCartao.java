package tech.jamersondev.microservicecartoes.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ClientCartao {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID clientCartaoIdentifier;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;
    private BigDecimal limite;

    public void CadastrarNovoCartao(UUID clientCartaoIdentifier, String cpf, Cartao cartao, BigDecimal limite){
       this.setClientCartaoIdentifier(clientCartaoIdentifier);
       this.setCpf(cpf);
       this.setCartao(cartao);
       this.setLimite(limite);
    }
}
