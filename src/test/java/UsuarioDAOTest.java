import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;
import org.example.util.DatabaseInitializer;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioDAOTest {

    private static UsuarioDAO usuarioDAO;

    @BeforeAll
    static void setup() {
        // Inicializar o banco de dados antes de todos os testes
        DatabaseInitializer.initializeDatabase();
        usuarioDAO = new UsuarioDAO();
    }

    @Test
    @Order(1)
    void testCriarUsuario() {
        Usuario usuario = new Usuario("Joao", "senha123", "Projeto A", 12);
        usuarioDAO.criarUsuario(usuario);

        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        assertFalse(usuarios.isEmpty(), "A lista de usuários não deve estar vazia.");
        assertEquals("Joao", usuarios.get(0).getNome(), "O nome do primeiro usuário deve ser 'Joao'.");
    }

    @Test
    @Order(2)
    void testAtualizarUsuario() {
        Usuario usuarioAtualizado = new Usuario("Joao", "novaSenha", "Projeto B", 24);
        usuarioDAO.atualizarUsuario("Joao", usuarioAtualizado);

        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        assertEquals("novaSenha", usuarios.get(0).getSenha(), "A senha do usuário deve ter sido atualizada.");
        assertEquals("Projeto B", usuarios.get(0).getProjetos(), "O projeto do usuário deve ter sido atualizado.");
    }

    @Test
    @Order(3)
    void testExcluirUsuario() {
        usuarioDAO.excluirUsuario("Joao");

        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        assertTrue(usuarios.isEmpty(), "A lista de usuários deve estar vazia após exclusão.");
    }

    @Test
    @Order(4)
    void testAutenticacao() {
        Usuario usuario = new Usuario("Alice", "senha123", "Projeto C", 6);
        usuarioDAO.criarUsuario(usuario);

        boolean autenticado = usuarioDAO.autenticarUsuario("Alice", "senha123");
        assertTrue(autenticado, "A autenticação deve retornar true para credenciais corretas.");

        boolean autenticadoInvalido = usuarioDAO.autenticarUsuario("Alice", "senhaErrada");
        assertFalse(autenticadoInvalido, "A autenticação deve retornar false para credenciais incorretas.");
    }
}
