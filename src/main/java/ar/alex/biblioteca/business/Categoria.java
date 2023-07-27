package ar.alex.biblioteca.business;

import java.util.Objects;

public abstract class Categoria {

   private String name;

   public Categoria(String name){
      this.name = name;
   }

   public abstract int getMaximoDiasPrestamo(CondicionPrestamoVisitor visitor);

   public String getName(){
      return this.name;
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
