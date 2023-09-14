package ar.alex.biblioteca;

import ar.alex.biblioteca.business.Biblioteca;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class app {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(app.class, args);
    }
}
