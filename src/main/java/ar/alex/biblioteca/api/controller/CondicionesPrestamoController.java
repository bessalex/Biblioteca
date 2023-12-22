package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.CategoriaDto;
import ar.alex.biblioteca.api.dto.CondicionesPrestamoDto;
import ar.alex.biblioteca.api.mapper.CondicionesPrestamoMapper;
import ar.alex.biblioteca.api.mapper.LibroMapper;
import ar.alex.biblioteca.business.model.CondicionPrestamoBO;
import ar.alex.biblioteca.data_access.entity.Categoria;
import ar.alex.biblioteca.business.service.CondicionesPrestamoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
public class CondicionesPrestamoController {

    private final CondicionesPrestamoService condicionesPrestamoService;

    private final CondicionesPrestamoMapper condicionesPrestamoMapper;

    @PostMapping("/library/loan-condition")
    public ResponseEntity<String> addConditionPrestamo  (@RequestBody CondicionesPrestamoDto condicionesPrestamoDto)
    {
        this.condicionesPrestamoService.save(this.condicionesPrestamoMapper.toBO(condicionesPrestamoDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/loan-condition/{id}")
    public ResponseEntity<CondicionesPrestamoDto> getCondicionPrestamo (@PathVariable Integer id){
        CondicionPrestamoBO condicionPrestamoBO = this.condicionesPrestamoService.findById(id);
        return ResponseEntity.ok(this.condicionesPrestamoMapper.toDto(condicionPrestamoBO));
    }
}
