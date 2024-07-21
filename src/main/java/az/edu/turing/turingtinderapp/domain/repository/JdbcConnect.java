package az.edu.turing.turingtinderapp.domain.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnect {
    final String url = "jdbc:postgresql://localhost:5433/postgres";
    final String user = "postgres";
    final String password = "Ali200aa";

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}