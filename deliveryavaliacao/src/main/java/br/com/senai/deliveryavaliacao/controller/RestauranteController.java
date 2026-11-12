package br.com.senai.deliveryavaliacao.controller;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.senai.deliveryavaliacao.service.RestauranteService;
import br.com.senai.deliveryavaliacao.model.Restaurante;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    public final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping
    public ResponseEntity<Restaurante> cadastrar(
       @Valid @RequestBody Restaurante restaurante) {

        Restaurante restauranteCadastrado = restauranteService.cadastrar(restaurante);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restauranteCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {

        return ResponseEntity.ok(restauranteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(
        @PathVariable Integer id) {
        return restauranteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(
        @PathVariable Integer id,
        @Valid @RequestBody Restaurante restauranteAtualizado) {

        return restauranteService.atualizar(id, restauranteAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        if (restauranteService.excluir(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}