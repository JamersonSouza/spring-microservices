package tech.jamersondev.microservicoavaliadorcredito.HandlerExceptions;

public class ClienteDadosNotFoundException  extends  Exception{
    public ClienteDadosNotFoundException() {
        super("Dados do cliente não encontrados no cpf informado");
    }
}
