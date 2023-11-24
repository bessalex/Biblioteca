package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.api.mapper.LibroMapper;
import ar.alex.biblioteca.business.model.LibroBO;
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
    @NonNull
    private final LibroMapper libroMapper;

    @GetMapping("/library/books/{isbn}")
    public ResponseEntity<LibroDto> getLibroByIsbn(@PathVariable String isbn) {
        LibroBO libro = this.libroService.findByIsbn(isbn);
        return ResponseEntity.ok(this.libroMapper.mapToLibroDto(libro));
    }

    @PostMapping("/library/books")
    public ResponseEntity<String> addLibro(@RequestBody LibroDto libroDTO) {
        libroService.save(this.libroMapper.mapToLibroBO(libroDTO));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/books/")
    public ResponseEntity<List<LibroDto>> getLibros(){
        return ResponseEntity.ok(this.libroMapper.mapToLibroDtoList(this.libroService.findAll()));
    }

    @GetMapping("/library/books")
    public ResponseEntity<List<LibroDto>> getLibrosByCategoria(@RequestParam(value = "category",
            required=false) String category) {
        return ResponseEntity.ok(
                this.libroMapper.mapToLibroDtoList(this.libroService.findByCategoria(category)));
    }
}
