package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Estudiante;

import java.util.*;

public class MapEstudianteRepository implements EstudianteRepository {


    private final Map<Integer, Estudiante> estudianteMap = new HashMap<>();
    @Override
    public void save(Estudiante estudiante) {

        this.estudianteMap.put(estudiante.getDni(),estudiante);
    }

    @Override
    public List<Estudiante> findAll() {
        return new ArrayList<>(this.estudianteMap.values());
    }


    @Override
    public Optional<Estudiante> findByDni(Integer dni) {

        if (this.estudianteMap.get(dni) == null)
            return Optional.empty();
        return Optional.of(new Estudiante(this.estudianteMap.get(dni)));
    }

}
