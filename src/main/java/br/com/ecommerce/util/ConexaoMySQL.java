package br.com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/Ecommerce_2024_01";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
