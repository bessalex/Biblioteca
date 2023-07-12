package ar.alex.biblioteca.business;

public class CondicionPrestamo {
    public static  int MAXIMO_DIAS_PRESTAMO_DEFAULT = 15;
    public static  int MAXIMO_DIAS_PRESTAMO_CATEGORIA_CIENCIA = 2;
    public static  int MAXIMO_RENOVACIONES_DEFAULT = 2;

    private Integer maxDiasPrestamo;

    public CondicionPrestamo(Libro libro){
        //  this.maxCantRenovaciones = this.MAXIMO_RENOVACIONES_DEFAULT;
        //this.maxDiasPrestamo = getMaximoDias(libro.getCategoria());

        this.maxDiasPrestamo = (libro.getCategoria() == Categoria.CIENCIA)
                ? MAXIMO_DIAS_PRESTAMO_CATEGORIA_CIENCIA
                : MAXIMO_DIAS_PRESTAMO_DEFAULT;

    }
/*
    private Integer getMaximoDias(CategoriaCiencia categoria){
        return 7;
    }

    private Integer getMaximoDias(CategoriaHistoria categoria){
        return 10;
    }
*/

    /*
    public static CondicionPrestamo getInstance(){
        if (instancia == null){
            instancia = new CondicionPrestamo();
        }
        return instancia;
    }
    */



    public boolean isRenovable(int nroRenovaciones){
        return nroRenovaciones < this.getMaximoCantRenovacion();
    }

    public Integer getMaximoDiasPrestamo(){
        return this.maxDiasPrestamo;
    }

    public Integer getMaximoCantRenovacion() {
        return MAXIMO_RENOVACIONES_DEFAULT;
    }
}