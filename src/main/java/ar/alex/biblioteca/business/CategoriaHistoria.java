package ar.alex.biblioteca.business;

import ar.alex.biblioteca.business.enums.CategoriaType;

public class CategoriaHistoria extends Categoria {

    private CategoriaHistoria() {
        super(CategoriaType.historia);
    }

    @Override
    public int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor) {
        return visitor.getMaximoDiasPrestamo(this);
    }
}
