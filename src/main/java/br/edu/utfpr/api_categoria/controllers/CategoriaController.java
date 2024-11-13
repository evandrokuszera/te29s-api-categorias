package br.edu.utfpr.api_categoria.controllers;

import br.edu.utfpr.api_categoria.dtos.CategoriaDTO;
import br.edu.utfpr.api_categoria.models.Categoria;
import br.edu.utfpr.api_categoria.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria novaCategoria = new Categoria();

        novaCategoria.setName(categoriaDTO.name());
        novaCategoria.setActive(categoriaDTO.active());
        novaCategoria.setCreatedAt(LocalDateTime.now());

        this.categoriaRepository.save(novaCategoria);

        return ResponseEntity.created(null).body(novaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAll(){
        List<CategoriaDTO> listaDTOs = new ArrayList<>();

        for (Categoria categoria : this.categoriaRepository.findAll()) {
            CategoriaDTO categoriaDTO = new CategoriaDTO(
                    categoria.getId(),
                    categoria.getName(),
                    categoria.getActive()
            );
            listaDTOs.add(categoriaDTO);
        }

        return ResponseEntity.ok( listaDTOs );
    }
}
