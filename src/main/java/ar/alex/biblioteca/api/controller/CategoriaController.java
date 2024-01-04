package ar.alex.biblioteca.api.controller;


import ar.alex.biblioteca.api.dto.CategoriaDto;
import ar.alex.biblioteca.data_access.entity.Categoria;
import ar.alex.biblioteca.business.service.CategoriaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class CategoriaController {

    @NonNull private final CategoriaService categoriaService;

    @PostMapping("/library/category")
    public ResponseEntity<String> addCategoria  (@RequestBody CategoriaDto categoriaDto){
        this.categoriaService.save(new Categoria(null, categoriaDto.nombre()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/category/{id}")
    public ResponseEntity<CategoriaDto> addCategoria  (@PathVariable Integer id){
        Categoria categoria = this.categoriaService.findById(id);
        return ResponseEntity.ok(new CategoriaDto(categoria.getNombre()));
    }
}
