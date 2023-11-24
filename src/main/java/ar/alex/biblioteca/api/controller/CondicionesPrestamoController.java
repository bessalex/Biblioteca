package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.CondicionesPrestamoDto;
import ar.alex.biblioteca.business.service.CategoriaService;
import ar.alex.biblioteca.business.service.CondicionesPrestamoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CondicionesPrestamoController {

    @NonNull private final CondicionesPrestamoService condicionesPrestamoService;

    @PostMapping("/library/loan-conditions")
    public ResponseEntity<String> addEstudiante(@RequestBody CondicionesPrestamoDto condicionesPrestamoDto){
        this.condicionesPrestamoService.save();
        return ResponseEntity.ok().build();
    }

}
