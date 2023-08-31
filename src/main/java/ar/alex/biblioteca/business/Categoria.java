package ar.alex.biblioteca.business;

import ar.alex.biblioteca.business.enums.CategoriaType;

import java.util.Objects;

public class Categoria {


   private final CategoriaType categoriaEnum;

   public Categoria(CategoriaType categoriaEnum){
      this.categoriaEnum = categoriaEnum;
   }

   public int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor){
      return visitor.getMaximoDiasPrestamo(this);
   }

   public String getName(){
      return categoriaEnum.getName();
   };

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Categoria)) return false;
      Categoria categoria = (Categoria) o;
      return Objects.equals(getName(), categoria.getName());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getName());
   }
}
