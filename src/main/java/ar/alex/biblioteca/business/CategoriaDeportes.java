package ar.alex.biblioteca.business;

public class CategoriaDeportes extends Categoria {

    public CategoriaDeportes() {
        super("DEPORTE");
    }

    @Override
    public int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor) {

        return visitor.getMaximoDiasPrestamo(this);
    }
}



