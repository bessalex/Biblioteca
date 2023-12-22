package ar.alex.biblioteca.business.model;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CondicionPrestamoBO {
    public static  int MAXIMO_DIAS_PRESTAMO_DEFAULT = 15;
    public static  int MAXIMO_DIAS_PRESTAMO_CATEGORIA_CIENCIA = 10;
    public static  int MAXIMO_RENOVACIONES_DEFAULT = 2;

    private final int id;
    private final int idCategoria;
    private final int numMaximoDiasPrestamo;
    private final int numMaximoRenovaciones;


    public boolean isRenovable(int nroRenovaciones){
        return nroRenovaciones < this.numMaximoRenovaciones;
    }

    public LocalDate getFechaMaximoDiasVencimiento(LocalDate fechaInicio) {
        return fechaInicio.plusDays(this.numMaximoDiasPrestamo);
    }


    /*
    public Integer getMaximoCantRenovacion() {
        return MAXIMO_RENOVACIONES_DEFAULT;
    }


    @Override
    public int getMaximoDiasPrestamo(Categoria_abstract categoria) {
        return MAXIMO_DIAS_PRESTAMO_DEFAULT;
    }
    @Override
    public int getMaximoDiasPrestamo(CategoriaCiencia categoriaCiencia) {
        return MAXIMO_DIAS_PRESTAMO_CATEGORIA_CIENCIA;
    }
    */

}