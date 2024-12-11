import org.example.dao.GrupoDAO;
import org.example.dao.UsuarioDAO;
import org.example.model.Grupo;
import org.example.model.Usuario;
import org.example.util.DatabaseInitializer;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GrupoDAOTest {

    private static GrupoDAO grupoDAO;
    private static UsuarioDAO usuarioDAO;

    @BeforeAll
    static void setup() {
        // Inicializar o banco de dados antes de todos os testes
        DatabaseInitializer.initializeDatabase();
        grupoDAO = new GrupoDAO();
        usuarioDAO = new UsuarioDAO();
    }

    @Test
    @Order(1)
    void testCriarGrupo() {
        // Criar usuários para o grupo
        Usuario usuario1 = new Usuario("Alice", "senha123", "Projeto A", 12);
        Usuario usuario2 = new Usuario("Bob", "senha456", "Projeto B", 6);
        usuarioDAO.criarUsuario(usuario1);
        usuarioDAO.criarUsuario(usuario2);

        // Criar um grupo com esses usuários
        List<Usuario> membros = new ArrayList<>();
        membros.add(usuario1);
        membros.add(usuario2);

        Grupo grupo = new Grupo("Equipe Desenvolvimento", "Desenvolvimento do sistema", membros);
        grupoDAO.criarGrupo(grupo);

        // Validar criação do grupo
        List<Grupo> grupos = grupoDAO.listarGrupos();
        assertFalse(grupos.isEmpty(), "A lista de grupos não deve estar vazia.");
        assertEquals("Equipe Desenvolvimento", grupos.get(0).getNome(), "O nome do grupo deve ser 'Equipe Desenvolvimento'.");
    }

    @Test
    @Order(2)
    void testAtualizarGrupo() {
        // Atualizar descrição do grupo
        List<Grupo> grupos = grupoDAO.listarGrupos();
        assertFalse(grupos.isEmpty(), "Deve haver pelo menos um grupo para atualizar.");

        Grupo grupoAtualizado = new Grupo("Equipe Desenvolvimento", "Desenvolvimento web", grupos.get(0).getMembros());
        grupoDAO.atualizarGrupo("Equipe Desenvolvimento", grupoAtualizado);

        // Validar atualização
        grupos = grupoDAO.listarGrupos();
        assertEquals("Desenvolvimento web", grupos.get(0).getDescricao(), "A descrição do grupo deve ter sido atualizada.");
    }

    @Test
    @Order(3)
    void testListarGruposDoUsuario() {
        // Listar grupos para Alice
        List<Grupo> gruposAlice = grupoDAO.listarGruposDoUsuario("Alice");
        assertFalse(gruposAlice.isEmpty(), "Alice deve estar associada a pelo menos um grupo.");
        assertEquals("Equipe Desenvolvimento", gruposAlice.get(0).getNome(), "O grupo deve ser 'Equipe Desenvolvimento'.");

        // Listar grupos para Bob
        List<Grupo> gruposBob = grupoDAO.listarGruposDoUsuario("Bob");
        assertFalse(gruposBob.isEmpty(), "Bob deve estar associado a pelo menos um grupo.");
    }

    @Test
    @Order(4)
    void testExcluirGrupo() {
        // Excluir o grupo existente
        grupoDAO.excluirGrupo("Equipe Desenvolvimento");

        // Validar exclusão
        List<Grupo> grupos = grupoDAO.listarGrupos();
        assertTrue(grupos.isEmpty(), "A lista de grupos deve estar vazia após a exclusão.");
    }
}
