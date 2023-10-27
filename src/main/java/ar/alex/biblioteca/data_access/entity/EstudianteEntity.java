package ar.alex.biblioteca.data_access.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@Table(name="estudiantes")
@RequiredArgsConstructor
public class EstudianteEntity {
    @Id
    @NonNull
    private final Integer dni;
    @NonNull
    private final String apellido;
    @NonNull
    private final String nombres;
    @NonNull
    private final String direccion;
}
