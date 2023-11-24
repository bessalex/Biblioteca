package ar.alex.biblioteca.api.controller;


import ar.alex.biblioteca.business.service.CategoriaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class CategoriaController {

    @NonNull private final CategoriaService categoriaService;

    @PostMapping("/library/category")
    public ResponseEntity<String> addEstudiante(@RequestBody String categoriaNombre){
        this.categoriaService.save(categoriaNombre);
        return ResponseEntity.ok().build();
    }

}
