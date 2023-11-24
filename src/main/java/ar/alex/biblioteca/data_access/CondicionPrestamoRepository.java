package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.data_access.entity.CategoriaEntity;
import ar.alex.biblioteca.data_access.entity.CondicionPrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicionPrestamoRepository extends JpaRepository<CondicionPrestamoEntity, Long>  {
}
