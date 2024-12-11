package org.example.main;

import org.example.util.DatabaseInitializer;
import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;
import org.example.dao.GrupoDAO;
import org.example.model.Grupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializar o banco de dados
        DatabaseInitializer.initializeDatabase();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        GrupoDAO grupoDAO = new GrupoDAO();

        // Adicionar usuários hardcoded
        Usuario alice = new Usuario("Alice", "12345", "Projeto Alpha", 10);
        Usuario bob = new Usuario("Bob", "54321", "Projeto Beta", 15);
        Usuario carol = new Usuario("Carol", "password", "Projeto Gamma", 20);

        usuarioDAO.criarUsuario(alice);
        usuarioDAO.criarUsuario(bob);
        usuarioDAO.criarUsuario(carol);

        // Adicionar grupos hardcoded
        List<Usuario> membrosGrupo1 = List.of(alice, bob, carol);
        List<Usuario> membrosGrupo2 = List.of(alice, carol);
        List<Usuario> membrosGrupo3 = List.of(bob, carol);

        grupoDAO.criarGrupo(new Grupo("Equipe Desenvolvimento", "Desenvolvimento do sistema principal", membrosGrupo1));
        grupoDAO.criarGrupo(new Grupo("Equipe Design", "Criação de interfaces", membrosGrupo2));
        grupoDAO.criarGrupo(new Grupo("Equipe Testes", "Garantia de qualidade", membrosGrupo3));

        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("""
             _____ _     _                                 _       _      _           _       _
            /  ___(_)   | |                               (_)     (_)    (_)         | |     | |
            \\ `--. _ ___| |_ ___ _ __ ___   __ _           _ _ __  _  ___ _  __ _  __| | ___ | |
             `--. \\ / __| __/ _ \\ '_ ` _ \\ / _` |         | | '_ \\| |/ __| |/ _` |/ _` |/ _ \\| |
            /\\__/ / \\__ \\ ||  __/ | | | | | (_| |         | | | | | | (__| | (_| | (_| | (_) |_|
            \\____/|_|___/\\__\\___|_| |_| |_|\\__,_|         |_|_| |_|_|\\___|_|\\__,_|\\__,_|\\___/(_)
            """
        );

        do {
            System.out.println("\n---------------- MENU ----------------");
            System.out.println("1. Criar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Excluir Usuário");
            System.out.println("5. Criar Grupo");
            System.out.println("6. Listar Grupos");
            System.out.println("7. Atualizar Grupo");
            System.out.println("8. Excluir Grupo");
            System.out.println("9. Autenticar Usuário");
            System.out.println("10. Listar Grupos de um Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Digite o projeto: ");
                    String projeto = scanner.nextLine();
                    System.out.print("Digite o tempo de atividade (em meses): ");
                    int tempoAtividade = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuario = new Usuario(nome, senha, projeto, tempoAtividade);
                    usuarioDAO.criarUsuario(usuario);
                }
                case 2 -> {
                    System.out.println("\nUsuários cadastrados:");
                    usuarioDAO.listarUsuarios().forEach(u ->
                            System.out.println(u.getNome() + " - " + u.getProjetos() + " - " + u.getTempoAtividadeMeses() + " meses")
                    );
                }
                case 3 -> {
                    System.out.print("Digite o nome do usuário a ser atualizado: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a nova senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Digite o novo projeto: ");
                    String projeto = scanner.nextLine();
                    System.out.print("Digite o novo tempo de atividade (em meses): ");
                    int tempoAtividade = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuarioAtualizado = new Usuario(nome, senha, projeto, tempoAtividade);
                    usuarioDAO.atualizarUsuario(nome, usuarioAtualizado);
                }
                case 4 -> {
                    System.out.print("Digite o nome do usuário a ser excluído: ");
                    String nome = scanner.nextLine();
                    usuarioDAO.excluirUsuario(nome);
                }
                case 5 -> {
                    System.out.print("Digite o nome do grupo: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a descrição do grupo: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Digite os nomes dos membros separados por vírgula: ");
                    String[] nomes = scanner.nextLine().split(",");
                    List<Usuario> membros = new ArrayList<>();
                    for (String nomeUsuario : nomes) {
                        membros.add(new Usuario(nomeUsuario.trim(), "", "", 0));
                    }
                    Grupo grupo = new Grupo(nome, descricao, membros);
                    grupoDAO.criarGrupo(grupo);
                }
                case 6 -> {
                    System.out.println("\nGrupos cadastrados:");
                    grupoDAO.listarGrupos().forEach(g ->
                            System.out.println(g.getNome() + " - " + g.getDescricao() + " - Membros: " +
                                    g.getMembros().stream().map(Usuario::getNome).reduce((a, b) -> a + ", " + b).orElse(""))
                    );
                }
                case 7 -> {
                    System.out.print("Digite o nome do grupo a ser atualizado: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a nova descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Digite os nomes dos membros separados por vírgula: ");
                    String[] nomes = scanner.nextLine().split(",");
                    List<Usuario> membros = new ArrayList<>();
                    for (String nomeUsuario : nomes) {
                        membros.add(new Usuario(nomeUsuario.trim(), "", "", 0));
                    }
                    Grupo grupoAtualizado = new Grupo(nome, descricao, membros);
                    grupoDAO.atualizarGrupo(nome, grupoAtualizado);
                }
                case 8 -> {
                    System.out.print("Digite o nome do grupo a ser excluído: ");
                    String nome = scanner.nextLine();
                    grupoDAO.excluirGrupo(nome);
                }
                case 9 -> {
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();
                    boolean autenticado = usuarioDAO.autenticarUsuario(nome, senha);
                    if (autenticado) {
                        System.out.println("Usuário autenticado com sucesso!");
                    } else {
                        System.out.println("Credenciais inválidas.");
                    }
                }
                case 10 -> {
                    System.out.print("Digite o nome do usuário para listar os grupos: ");
                    String nomeUsuario = scanner.nextLine();
                    System.out.println("\nGrupos do usuário " + nomeUsuario + ":");
                    grupoDAO.listarGruposDoUsuario(nomeUsuario).forEach(g ->
                            System.out.println(g.getNome() + " - " + g.getDescricao())
                    );
                }
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
