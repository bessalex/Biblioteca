package ar.alex.biblioteca.business;

public interface CondicionPrestamoVisitor {
    int getMaximoDiasPrestamo(CategoriaClasico categoriaClasico);
    int getMaximoDiasPrestamo(CategoriaCiencia categoriaCiencia);
    int getMaximoDiasPrestamo(CategoriaDeportes categoriaDeportes);
    int getMaximoDiasPrestamo(CategoriaHistoria categoriaHistoria);
}
