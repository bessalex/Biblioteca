package ar.alex.biblioteca.old.enums;
/*/
import ar.alex.biblioteca.old.CategoriaCiencia;
import ar.alex.biblioteca.old.CategoriaClasico;
import ar.alex.biblioteca.old.CategoriaDeportes;
import ar.alex.biblioteca.old.CategoriaHistoria;


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
/*/