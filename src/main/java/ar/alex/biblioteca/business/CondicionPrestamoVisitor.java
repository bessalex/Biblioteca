package ar.alex.biblioteca.business;

public interface CondicionPrestamoVisitor {
    int getMaximoDiasPrestamo(Categoria categoria);
    int getMaximoDiasPrestamo(CategoriaCiencia categoriaCiencia);
}
