package ar.alex.biblioteca.business;

public interface CondicionPrestamoVisitor {
    int getMaximoDiasPrestamo(Categoria_abstract categoria);
    int getMaximoDiasPrestamo(CategoriaCiencia categoriaCiencia);
}
