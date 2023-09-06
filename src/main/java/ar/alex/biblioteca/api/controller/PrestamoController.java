package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.PrestamoDto;
import ar.alex.biblioteca.business.*;
import ar.alex.biblioteca.business.exceptions.PrestamoDuplicadoException;
import ar.alex.biblioteca.business.exceptions.PrestamoSuperaRenovacionesException;
import ar.alex.biblioteca.business.exceptions.PrestamoVencidoException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PrestamoController {

    private final Biblioteca biblioteca = Biblioteca.getInstance();

    @PostMapping("/library/loan")
    public ResponseEntity<PrestamoDto> getLoan(@RequestBody PrestamoDto prestamoDto)
            throws ReflectiveOperationException, PrestamoDuplicadoException {
        return ResponseEntity.ok(new PrestamoDto(biblioteca.solicitarPrestamo(
                new Libro(prestamoDto.getIsbn(), "", Categoria.create("clasico"),""),
                new Estudiante(prestamoDto.getDni(),"","","")
        )));
    }

    @PostMapping("/library/loan/renew")
    public ResponseEntity<PrestamoDto> loanRenew(@RequestBody PrestamoDto prestamoDto) throws ReflectiveOperationException, PrestamoVencidoException, PrestamoSuperaRenovacionesException {
        return ResponseEntity.ok(new PrestamoDto(biblioteca.renovarPrestamo(
                new Libro(prestamoDto.getIsbn(), "", Categoria.create("clasico"),""),
                new Estudiante(prestamoDto.getDni(),"","",""))));
    }


}
