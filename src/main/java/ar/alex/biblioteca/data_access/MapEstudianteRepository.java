package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.api.dto.EstudianteDto;
import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.Libro;

import java.util.*;
import java.util.stream.Collectors;

public class MapEstudianteRepository  {


    private final Map<Integer, Estudiante> estudianteMap = new HashMap<>();

    public void save(Estudiante estudiante) {

        this.estudianteMap.put(estudiante.getDni(),estudiante);
    }


    public List<Estudiante> findAll() {
        return new ArrayList<>(this.estudianteMap.values());
    }



    public Optional<Estudiante> findByDni(Integer dni) {

        if (this.estudianteMap.get(dni) == null)
            return Optional.empty();
        return Optional.of(new Estudiante(this.estudianteMap.get(dni)));
    }

}
