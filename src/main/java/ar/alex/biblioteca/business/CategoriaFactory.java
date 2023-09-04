package ar.alex.biblioteca.business;

import ar.alex.biblioteca.business.enums.CategoriaType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CategoriaFactory {

    public static Categoria createCategoria(String name) throws InstantiationException, IllegalAccessException {
        CategoriaType categoriaType = Enum.valueOf(CategoriaType.class, name.toLowerCase());
        return createCategoria(categoriaType);
    }

    public static Categoria createCategoria(CategoriaType categoriaType) throws InstantiationException, IllegalAccessException {
        return categoriaType.create();
        /*
        switch(categoriaType){
            case ciencia -> { return new CategoriaCiencia(); }
            default -> {return new Categoria(categoriaType);}
        }
    */
    }

}
