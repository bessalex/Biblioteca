package ar.alex.biblioteca.business.enums;

import ar.alex.biblioteca.business.*;



public enum CategoriaType {
    ciencia("ciencia", CategoriaCiencia.class),
    historia("historia", CategoriaHistoria.class),
    deportes("deportes", CategoriaDeportes.class),
    clasico("clasico", CategoriaClasico.class);

    private final String name;
    private final Class<? extends Categoria_abstract> categoria;

    CategoriaType( String name, Class<? extends Categoria_abstract> categoria) {
        this.categoria = categoria;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }


    public Categoria_abstract create() throws ReflectiveOperationException {
        return this.categoria.getDeclaredConstructor().newInstance();
    }

}
