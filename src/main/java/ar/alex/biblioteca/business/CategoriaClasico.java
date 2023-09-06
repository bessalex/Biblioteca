package ar.alex.biblioteca.business;

import ar.alex.biblioteca.business.enums.CategoriaType;

public class CategoriaClasico extends Categoria {

    public CategoriaClasico() {
        super(CategoriaType.clasico);
    }



    @Override
    public int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor) {
        return visitor.getMaximoDiasPrestamo(this);
    }
}
