package tech.jamersondev.microservicoavaliadorcredito.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseAvaliacaoCliente {

    private List<CartaoAprovadoDTO> cartoes;

}
