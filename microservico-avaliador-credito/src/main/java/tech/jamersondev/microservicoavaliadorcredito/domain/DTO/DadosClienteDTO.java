package tech.jamersondev.microservicoavaliadorcredito.domain.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DadosClienteDTO {
    private UUID clientIdentifier;
    private String nome;
    private Integer idadeClient;
}
