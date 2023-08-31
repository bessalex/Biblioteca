package ar.alex.biblioteca.business;

import ar.alex.biblioteca.business.enums.CategoriaType;

public class CategoriaDeportes extends Categoria {

    public CategoriaDeportes() {
        super(CategoriaType.deportes);
    }

    @Override
    public int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor) {

        return visitor.getMaximoDiasPrestamo(this);
    }
}



