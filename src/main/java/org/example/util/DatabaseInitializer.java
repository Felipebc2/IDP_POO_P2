package org.example.util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        // SQL para limpar os dados das tabelas existentes
        String truncateTables = """
            TRUNCATE TABLE Grupo;
            TRUNCATE TABLE Usuario;
        """;

        // SQL para criar as tabelas, caso não existam
        String createUsuarioTable = """
            CREATE TABLE IF NOT EXISTS Usuario (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nome VARCHAR(255) NOT NULL,
                senha VARCHAR(255) NOT NULL,
                projetos TEXT,
                tempoAtividadeMeses INT
            );
        """;

        String createGrupoTable = """
            CREATE TABLE IF NOT EXISTS Grupo (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nome VARCHAR(255) NOT NULL,
                descricao TEXT,
                membros TEXT
            );
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Limpar os dados das tabelas (sem excluir a estrutura)
            statement.execute(truncateTables);

            // Garantir que as tabelas existem
            statement.execute(createUsuarioTable);
            statement.execute(createGrupoTable);

            System.out.println("Banco de dados limpo e inicializado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }
}
