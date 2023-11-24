package ar.alex.biblioteca.business;

import ar.alex.biblioteca.business.enums.CategoriaType;

import java.util.Objects;
/*
public abstract class Categoria_abstract {

   private final CategoriaType categoriaEnum;

   protected Categoria_abstract(CategoriaType categoriaEnum){
      this.categoriaEnum = categoriaEnum;
   }

   public abstract int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor);

   public static Categoria_abstract create(CategoriaType categoriaType) throws ReflectiveOperationException {
      return categoriaType.create();
   }

   public static Categoria_abstract create(String name)  {
      CategoriaType categoriaType = Enum.valueOf(CategoriaType.class, name.toLowerCase());
      try {
         return categoriaType.create();
      } catch (ReflectiveOperationException e) {
         throw new RuntimeException(e);
      }
   }
   public String getName(){
      return categoriaEnum.getName();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Categoria_abstract categoria)) return false;
      return Objects.equals(getName(), categoria.getName());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getName());
   }
}
*/