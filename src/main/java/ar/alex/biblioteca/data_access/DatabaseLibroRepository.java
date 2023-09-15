package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.*;
import java.sql.ResultSet;

public class DatabaseLibroRepository implements LibroRepository {

    @Override
    public void save(Libro libro) {
        Connection connection = DatabaseConnection.getConnection();

        try {
            String insertSQL = "INSERT INTO libros (isbn, titulo, autor, categoria, ejemplares_disponibles) VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, libro.getIsbn());
            preparedStatement.setString(2, libro.getTitulo());
            preparedStatement.setString(3, libro.getAutor());
            preparedStatement.setString(4, libro.getCategoria().getName());
            preparedStatement.setInt(5, libro.getEjemplares_disponibles());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseConnection.cerrarConexion();
        }

    }
        @Override
    public List<Libro> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<Libro> findByCategoria(Categoria categoria) {
        return  new ArrayList<>();
    }

    @Override
    public Optional<Libro> findByIsbn(String isbn) {

        String consultaSQL = "SELECT * FROM libros WHERE isbn = ?";
        Connection connection = DatabaseConnection.getConnection();

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setString(1, isbn);

            try (
                ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Libro libro = new Libro(resultSet.getString("isbn"),
                            resultSet.getString("titulo"),
                            Categoria.create(resultSet.getString("categoria")),
                            resultSet.getString("autor"));
                    return Optional.of(libro);
                }
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DatabaseConnection.cerrarConexion();
        }
        return Optional.empty();
    }




    @Override
    public void update(Libro libro) {
        Connection connection = DatabaseConnection.getConnection();

        DatabaseConnection.cerrarConexion();
    }
}
