package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.PrestamoDto;
import ar.alex.biblioteca.business.*;
import ar.alex.biblioteca.business.exceptions.EstudianteNoPresenteException;
import ar.alex.biblioteca.business.exceptions.PrestamoDuplicadoException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PrestamoController {

    private final Biblioteca biblioteca = Biblioteca.getInstance();

    @PostMapping("/library/loan")
    public ResponseEntity<PrestamoDto> getLoan(@RequestBody PrestamoDto prestamoDto) {
        return ResponseEntity.ok(
                new PrestamoDto(biblioteca.solicitarPrestamo(prestamoDto.getIsbn(),prestamoDto.getDni())));
    }

    @PatchMapping("/library/loan/{id}")
    public ResponseEntity<PrestamoDto> loanRenew(@PathVariable String id,@RequestBody PrestamoDto prestamoDto)  {
        return ResponseEntity.ok(new PrestamoDto(
                biblioteca.renovarPrestamo(id, prestamoDto.getIsbn(), prestamoDto.getDni())));
    }


}
