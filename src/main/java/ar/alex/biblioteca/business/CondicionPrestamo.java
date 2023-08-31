package ar.alex.biblioteca.business;

public class CondicionPrestamo implements CondicionPrestamoVisitor{
    public static  int MAXIMO_DIAS_PRESTAMO_DEFAULT = 15;
    public static  int MAXIMO_DIAS_PRESTAMO_CATEGORIA_CIENCIA = 10;
    public static  int MAXIMO_RENOVACIONES_DEFAULT = 2;


    public boolean isRenovable(int nroRenovaciones){
        return nroRenovaciones < this.getMaximoCantRenovacion();
    }


    public Integer getMaximoCantRenovacion() {
        return MAXIMO_RENOVACIONES_DEFAULT;
    }


    @Override
    public int getMaximoDiasPrestamo(Categoria categoria) {
        return MAXIMO_DIAS_PRESTAMO_DEFAULT;
    }
    @Override
    public int getMaximoDiasPrestamo(CategoriaCiencia categoriaCiencia) {
        return MAXIMO_DIAS_PRESTAMO_CATEGORIA_CIENCIA;
    }

}