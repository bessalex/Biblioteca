package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.exceptions.CategoriaNoPresenteException;
import ar.alex.biblioteca.data_access.entity.Categoria;
import ar.alex.biblioteca.data_access.CategoriaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    @NonNull
    private final CategoriaRepository categoriaRepository;

    public void save(Categoria categoria) {
        if (this.categoriaRepository.findByNombre(categoria.getNombre()).isEmpty())
            this.categoriaRepository.save(categoria);
    }

    public Categoria findIdByNombre(String nombre)  {

        List<Categoria> resultado = this.categoriaRepository.findByNombre(nombre);

        if (resultado.isEmpty())
           throw new CategoriaNoPresenteException(nombre);

        return resultado.get(0);
    }

    public Categoria findById(Integer id)  {

        Categoria categoria = this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoPresenteException(id));
        System.out.println("categoria.getNombre() = " + categoria.getNombre());
        return categoria;
    }

}
