package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.model.Libro;
import ar.alex.biblioteca.business.service.LibroService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LibroController {

    @NonNull
    private final LibroService libroService;

    @GetMapping("/library/books/{isbn}")
    public ResponseEntity<LibroDto> getLibroByIsbn(@PathVariable String isbn) {
        Libro libro = this.libroService.findByIsbn(isbn);
        return ResponseEntity.ok(LibroDto.builder()
                .isbn(libro.getIsbn())
                .titulo(libro.getTitulo())
                .categoria(libro.getCategoria().toString())
                .build());
    }

    @PostMapping("/library/books")
    public ResponseEntity<String> addLibro(@RequestBody LibroDto libroDTO) {
        libroService.save(new Libro(libroDTO.getIsbn(),
                libroDTO.getTitulo(),
                Categoria.create(libroDTO.getCategoria()),
                libroDTO.getAutor()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/books/")
    public ResponseEntity<List<LibroDto>> getLibros(){
        return ResponseEntity.ok(this.libroService.findAll()
                .stream()
                .map(libro  -> LibroDto.builder()
                        .isbn(libro.getIsbn())
                        .titulo(libro.getTitulo())
                        .autor(libro.getAutor())
                        .categoria(libro.getCategoria().toString())
                        .build()).toList());
    }

// mapstruct <-- mapper  https://www.baeldung.com/mapstruct
    @GetMapping("/library/books")
    public ResponseEntity<List<LibroDto>> getLibrosByCategoria(@RequestParam(value = "category", required=false) String category) {
        return ResponseEntity.ok(this.libroService.findByCategoria(category)
                .stream().map(libro  -> LibroDto.builder()
                        .isbn(libro.getIsbn())
                        .titulo(libro.getTitulo())
                        .autor(libro.getAutor())
                        .categoria(libro.getCategoria().toString())
                        .build()).toList());
    }
}
