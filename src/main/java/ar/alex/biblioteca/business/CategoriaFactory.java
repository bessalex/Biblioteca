package ar.alex.biblioteca.business;

import ar.alex.biblioteca.business.enums.CategoriaType;

public class CategoriaFactory {

    public static Categoria createCategoria(String name)  {
        CategoriaType categoriaType = Enum.valueOf(CategoriaType.class, name.toLowerCase());
        return createCategoria(categoriaType);
    }

    public static Categoria createCategoria(CategoriaType categoriaType){
        switch(categoriaType){
            case ciencia -> { return new CategoriaCiencia(); }
            default -> {return new Categoria(categoriaType);}
        }
    }

}
