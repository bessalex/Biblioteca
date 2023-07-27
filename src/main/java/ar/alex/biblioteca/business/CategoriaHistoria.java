package ar.alex.biblioteca.business;

public class CategoriaHistoria extends Categoria {

    public CategoriaHistoria() {
        super("HISTORIA");
    }

    @Override
    public int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor) {
        return visitor.getMaximoDiasPrestamo(this);
    }
}
