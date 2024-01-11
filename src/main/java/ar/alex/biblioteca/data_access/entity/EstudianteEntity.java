package ar.alex.biblioteca.data_access.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Entity
@Table(name="estudiantes")
@AllArgsConstructor
public class EstudianteEntity implements Serializable {
    @Id
    private final Integer dni;
    private final String apellido;
    private final String nombres;
    private final String direccion;
}
