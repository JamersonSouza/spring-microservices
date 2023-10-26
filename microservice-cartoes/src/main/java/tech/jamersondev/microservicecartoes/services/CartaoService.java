package tech.jamersondev.microservicecartoes.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jamersondev.microservicecartoes.domain.Cartao;
import tech.jamersondev.microservicecartoes.repositorys.CartaoRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @Transactional
    public Cartao saveCartao(Cartao cartao){
        return cartaoRepository.save(cartao);
    }

    public List<Cartao> getCartoesRenda(Long renda){
        var rendaToBigDecimal = BigDecimal.valueOf(renda);
        return cartaoRepository.findByRendaLessThanEqual(rendaToBigDecimal);
    }

}
