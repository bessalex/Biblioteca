package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.PrestamoDto;
import ar.alex.biblioteca.business.service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;

    @PostMapping("/library/loan")
    public ResponseEntity<PrestamoDto> getLoan(@RequestBody PrestamoDto prestamoDto) {
        return ResponseEntity.ok(
                new PrestamoDto(
                        this.prestamoService.alta(prestamoDto.getIsbn(),prestamoDto.getDni())));
    }

    @PatchMapping("/library/loan/{id}")
    public ResponseEntity<PrestamoDto> loanRenew(@PathVariable String id,@RequestBody PrestamoDto prestamoDto)  {
        return ResponseEntity.ok(new PrestamoDto(
                this.prestamoService.renovar(id)));
    }


}
