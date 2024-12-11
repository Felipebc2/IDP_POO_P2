package org.example.dao;

import org.example.model.Usuario;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para criar um novo usuário
    public void criarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, senha, projetos, tempoAtividadeMeses) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getProjetos());
            statement.setInt(4, usuario.getTempoAtividadeMeses());
            statement.executeUpdate();
            System.out.println(" - Usuário criado com sucesso!");
        } catch (SQLException e) {
            System.out.println(" !! Erro ao criar usuário: " + e.getMessage());
        }
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("nome"),
                        resultSet.getString("senha"),
                        resultSet.getString("projetos"),
                        resultSet.getInt("tempoAtividadeMeses")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(" !! Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Método para atualizar um usuário existente
    public void atualizarUsuario(String nome, Usuario usuarioAtualizado) {
        String sql = "UPDATE Usuario SET senha = ?, projetos = ?, tempoAtividadeMeses = ? WHERE nome = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuarioAtualizado.getSenha());
            statement.setString(2, usuarioAtualizado.getProjetos());
            statement.setInt(3, usuarioAtualizado.getTempoAtividadeMeses());
            statement.setString(4, nome);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(" - Usuário atualizado com sucesso!");
            } else {
                System.out.println(" !! Nenhum usuário encontrado com o nome fornecido.");
            }
        } catch (SQLException e) {
            System.out.println(" !! Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // Método para excluir um usuário
    public void excluirUsuario(String nome) {
        String sql = "DELETE FROM Usuario WHERE nome = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(" - Usuário excluído com sucesso!");
            } else {
                System.out.println(" !! Nenhum usuário encontrado com o nome fornecido.");
            }
        } catch (SQLException e) {
            System.out.println(" !! Erro ao excluir usuário: " + e.getMessage());
        }
    }

    // Método para autenticar o usuário
    public boolean autenticarUsuario(String nome, String senha) {
        String sql = "SELECT * FROM Usuario WHERE nome = ? AND senha = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, senha);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println(" - Usuário autenticado com sucesso!");
                    return true;
                } else {
                    System.out.println(" !! Nome de usuário ou senha incorretos.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(" !! Erro ao autenticar usuário: " + e.getMessage());
            return false;
        }
    }

}