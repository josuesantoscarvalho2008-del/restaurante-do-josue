package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;
import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService(ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Optional<Produto> cadastrar(
        ProdutoRequest produtoEncontrado) {

        Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(produtoEncontrado.getRestauranteId());

        if (restauranteEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = new Produto();

        produto.setNome(produtoEncontrado.getNome());
        produto.setPreco(produtoEncontrado.getPreco());
        produto.setRestaurante(restauranteEncontrado.get());

       
        return Optional.of(produtoRepository.save(produto));
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> atualizar(
        Integer id, ProdutoRequest produtoAtualizado) {

        Optional<Produto> produtoEncontrado = produtoRepository.findById(id);

        if (produtoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = produtoEncontrado.get();

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setRestaurante(restauranteRepository.findById(produtoAtualizado.getRestauranteId()).orElse(null));

        return Optional.of(produtoRepository.save(produto));
    }

    public boolean excluir(Integer id) {
       
        if (!produtoRepository.existsById(id)) {
            return false;
        }

        produtoRepository.deleteById(id);
        return true;
    }
    
}