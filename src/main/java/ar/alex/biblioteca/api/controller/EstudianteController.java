package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.EstudianteDto;
import ar.alex.biblioteca.business.Biblioteca;
import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.exceptions.EstudianteNoPresenteException;
import ar.alex.biblioteca.business.service.EstudianteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class EstudianteController {

    @NonNull private final  EstudianteService estudianteService;

    @GetMapping("/library/student/{dni}")
    public ResponseEntity<EstudianteDto> getEstudianteByDni(@PathVariable Integer dni) {
        return ResponseEntity.ok(new EstudianteDto(this.estudianteService.findByDni(dni)));
    }

    @PostMapping("/library/student")
    public ResponseEntity<String> addEstudiante(@RequestBody EstudianteDto estudianteDto){
        this.estudianteService.save(new Estudiante(estudianteDto.getDni(),
                estudianteDto.getApellido(),
                estudianteDto.getNombres(),
                estudianteDto.getDireccion()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/students")
    public ResponseEntity<List<EstudianteDto>> getEstudiantes(){
        return ResponseEntity.ok(estudianteService.findAll()
                .stream().map(EstudianteDto::new).collect(Collectors.toList()));
    }
}
