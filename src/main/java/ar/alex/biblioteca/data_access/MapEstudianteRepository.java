package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Estudiante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Estudiante findByDni(Integer dni) {

        if (this.estudianteMap.get(dni) == null)
            return null;
        return new Estudiante (this.estudianteMap.get(dni));
    }

}
