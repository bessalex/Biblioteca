package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.data_access.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EstudianteRepository  extends JpaRepository<EstudianteEntity, Integer> {

}
