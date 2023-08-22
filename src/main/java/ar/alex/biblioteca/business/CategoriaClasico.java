package ar.alex.biblioteca.business;

public class CategoriaClasico extends Categoria {

    public CategoriaClasico() {
        super("CLASICO");
    }

    @Override
    public int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor) {
        return visitor.getMaximoDiasPrestamo(this);
    }
}
