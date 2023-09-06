package ar.alex.biblioteca.business;

import ar.alex.biblioteca.business.enums.CategoriaType;

import java.util.Objects;

public abstract class Categoria {

   private final CategoriaType categoriaEnum;

   protected Categoria(CategoriaType categoriaEnum){
      this.categoriaEnum = categoriaEnum;
   }

   public abstract int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor);

   public static Categoria create(CategoriaType categoriaType) throws ReflectiveOperationException {
      return categoriaType.create();
   }

   public static Categoria create(String name) throws ReflectiveOperationException {
      CategoriaType categoriaType = Enum.valueOf(CategoriaType.class, name.toLowerCase());
      return categoriaType.create();
   }
   public String getName(){
      return categoriaEnum.getName();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Categoria categoria)) return false;
      return Objects.equals(getName(), categoria.getName());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getName());
   }
}
