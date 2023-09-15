package ar.alex.biblioteca.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    // Configura la información de la base de datos
    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    // Método para establecer la conexión a la base de datos
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    // Método para cerrar la conexión a la base de datos
    public static void cerrarConexion()  {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
