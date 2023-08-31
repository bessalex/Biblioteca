package ar.alex.biblioteca.business.enums;

public enum CategoriaType {
    ciencia("ciencia"),
    historia("historia"),
    deportes("deportes"),
    clasico("clasico");

    private final String name;

    CategoriaType(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
