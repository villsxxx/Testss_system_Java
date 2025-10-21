package testsystem.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties PROPERTIES = new Properties();
    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Не найден application.properties");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки application.properties", e);
        }
    }

    public static String URL = PROPERTIES.getProperty("db.url");
    public static String USER = PROPERTIES.getProperty("db.username");
    public static String PASSWORD = PROPERTIES.getProperty("db.password");

    // Метод для получения соединения
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private DatabaseConfig(){}

}
