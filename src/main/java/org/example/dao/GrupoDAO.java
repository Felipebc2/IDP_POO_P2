package org.example.dao;

import org.example.model.Grupo;
import org.example.model.Usuario;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrupoDAO {

    // Método para criar um novo grupo
    public void criarGrupo(Grupo grupo) {
        String sql = "INSERT INTO Grupo (nome, descricao, membros) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, grupo.getNome());
            statement.setString(2, grupo.getDescricao());
            statement.setString(3, getMembrosAsString(grupo.getMembros()));
            statement.executeUpdate();
            System.out.println(" - Grupo criado com sucesso!");
        } catch (SQLException e) {
            System.out.println(" !! Erro ao criar grupo: " + e.getMessage());
        }
    }

    // Método para listar todos os grupos
    public List<Grupo> listarGrupos() {
        List<Grupo> grupos = new ArrayList<>();
        String sql = "SELECT * FROM Grupo";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Grupo grupo = new Grupo(
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        getMembrosAsList(resultSet.getString("membros"))
                );
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            System.out.println(" !! Erro ao listar grupos: " + e.getMessage());
        }
        return grupos;
    }

    // Método para atualizar um grupo
    public void atualizarGrupo(String nome, Grupo grupoAtualizado) {
        String sql = "UPDATE Grupo SET descricao = ?, membros = ? WHERE nome = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, grupoAtualizado.getDescricao());
            statement.setString(2, getMembrosAsString(grupoAtualizado.getMembros()));
            statement.setString(3, nome);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(" - Grupo atualizado com sucesso!");
            } else {
                System.out.println(" !! Nenhum grupo encontrado com o nome fornecido.");
            }
        } catch (SQLException e) {
            System.out.println(" !! Erro ao atualizar grupo: " + e.getMessage());
        }
    }

    // Método para excluir um grupo
    public void excluirGrupo(String nome) {
        String sql = "DELETE FROM Grupo WHERE nome = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(" - Grupo excluído com sucesso!");
            } else {
                System.out.println(" !! Nenhum grupo encontrado com o nome fornecido.");
            }
        } catch (SQLException e) {
            System.out.println(" !! Erro ao excluir grupo: " + e.getMessage());
        }
    }

    // Método utilitário para converter lista de membros para String
    private String getMembrosAsString(List<Usuario> membros) {
        StringBuilder membrosString = new StringBuilder();
        for (Usuario membro : membros) {
            if (membrosString.length() > 0) {
                membrosString.append(",");
            }
            membrosString.append(membro.getNome());
        }
        return membrosString.toString();
    }

    // Método utilitário para converter String de membros para lista
    private List<Usuario> getMembrosAsList(String membrosString) {
        List<Usuario> membros = new ArrayList<>();
        if (membrosString != null && !membrosString.isEmpty()) {
            String[] nomes = membrosString.split(",");
            for (String nome : nomes) {
                membros.add(new Usuario(nome, "", "", 0)); // Apenas nome é relevante aqui
            }
        }
        return membros;
    }
    public List<Grupo> listarGruposDoUsuario(String nomeUsuario) {
        List<Grupo> grupos = new ArrayList<>();
        String sql = "SELECT * FROM Grupo WHERE membros LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nomeUsuario + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Grupo grupo = new Grupo(
                            resultSet.getString("nome"),
                            resultSet.getString("descricao"),
                            getMembrosAsList(resultSet.getString("membros"))
                    );
                    grupos.add(grupo);
                }
            }
        } catch (SQLException e) {
            System.out.println(" !! Erro ao listar grupos do usuário: " + e.getMessage());
        }
        return grupos;
    }
}