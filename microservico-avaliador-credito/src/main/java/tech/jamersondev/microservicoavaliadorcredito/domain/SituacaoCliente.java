package tech.jamersondev.microservicoavaliadorcredito.domain;

import lombok.*;
import tech.jamersondev.microservicoavaliadorcredito.domain.DTO.CartoesClienteDTO;
import tech.jamersondev.microservicoavaliadorcredito.domain.DTO.DadosClienteDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituacaoCliente {
    private DadosClienteDTO dadosCliente;
    private List<CartoesClienteDTO> cartoesClientes;

}
