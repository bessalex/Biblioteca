package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.EstudianteDto;
import ar.alex.biblioteca.api.mapper.EstudianteMapper;
import ar.alex.biblioteca.business.model.EstudianteBO;
import ar.alex.biblioteca.business.service.EstudianteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EstudianteController {

    @NonNull private final  EstudianteService estudianteService;
    @NonNull private final EstudianteMapper estudianteMapper;

    @GetMapping("/library/student/{dni}")
    public ResponseEntity<EstudianteDto> getEstudianteByDni(@PathVariable Integer dni) {
        EstudianteBO estudianteBO = this.estudianteService.findByDni(dni);
        return ResponseEntity.ok(this.estudianteMapper.mapToEstudianteDto(estudianteBO));
    }

    @PostMapping("/library/student")
    public ResponseEntity<String> addEstudiante(@RequestBody EstudianteDto estudianteDto){
        this.estudianteService.save(this.estudianteMapper.mapToEstudianteBO(estudianteDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/students")
    public ResponseEntity<List<EstudianteDto>> getEstudiantes(){
        return ResponseEntity.ok(
                    this.estudianteMapper.mapToEstudianteDtoList(estudianteService.findAll()));
    }
}
