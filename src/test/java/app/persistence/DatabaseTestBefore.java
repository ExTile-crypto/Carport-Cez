package app.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.fail;

// Fælles base-klasse for database-tests (kører mod "test"-schemaet)

public class DatabaseTestBefore {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=test";
    private static final String DB = "carport";

    protected static ConnectionPool connectionPool;

    // Kører kun en gang før alle test
    @BeforeAll
    static void setUpClass() {
        connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);
        // Opret mappers her
    }

    // Kører hver gang for alle test
    @BeforeEach
    void setUp() {
        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement()) {

            // Ryd tabeller, nulstil sekvenser og indsæt testdata her

        } catch (SQLException e) {
            fail("Database setup failed: " + e.getMessage());
        }
    }
}
