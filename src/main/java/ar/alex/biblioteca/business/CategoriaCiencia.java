package ar.alex.biblioteca.business;

import ar.alex.biblioteca.business.enums.CategoriaType;

public class CategoriaCiencia extends Categoria {


    public CategoriaCiencia() {
        super(CategoriaType.ciencia);
    }

    @Override
    public int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor) {
        return visitor.getMaximoDiasPrestamo(this);
    }

}