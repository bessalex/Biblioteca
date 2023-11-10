package ar.alex.biblioteca.data_access.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Builder
@Table(name="estudiantes")
@AllArgsConstructor
public class EstudianteEntity {
    @Id
    private final Integer dni;
    private final String apellido;
    private final String nombres;
    private final String direccion;
}
