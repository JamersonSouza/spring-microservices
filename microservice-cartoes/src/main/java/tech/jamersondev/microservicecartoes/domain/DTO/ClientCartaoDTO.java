package tech.jamersondev.microservicecartoes.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tech.jamersondev.microservicecartoes.domain.ClientCartao;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ClientCartaoDTO {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static ClientCartaoDTO fromModel(ClientCartao clientCartao){
            return new ClientCartaoDTO(clientCartao.getCartao().getNome(), clientCartao.getCartao().getBandeiraCartao().toString(),
                    clientCartao.getLimite());
    }
}
