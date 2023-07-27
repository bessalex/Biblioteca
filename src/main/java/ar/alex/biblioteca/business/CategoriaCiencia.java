package ar.alex.biblioteca.business;

public class CategoriaCiencia extends Categoria {

    public CategoriaCiencia() {
        super("CIENCIA");
    }

    @Override
    public int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor) {
        return visitor.getMaximoDiasPrestamo(this);
    }

}
