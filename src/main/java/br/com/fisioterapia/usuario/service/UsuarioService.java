package br.com.fisioterapia.usuario.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.com.fisioterapia.usuario.dto.UsuarioRespostaDTO;
import br.com.fisioterapia.usuario.model.Usuario;
import br.com.fisioterapia.usuario.model.UsuarioPermissaoTela;
import br.com.fisioterapia.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    

    // Injeção via construtor (Melhor prática que @Autowired)
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioRespostaDTO cadastrarNovoUsuario(UsuarioCadastroDTO dto) {
        // 1. Cria a entidade base
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setFuncao(dto.funcao().toUpperCase());
        usuario.setAtivo(true);
        
        if ("FISIOTERAPEUTA".equals(usuario.getFuncao())) {
            usuario.setCrefitoNumero(dto.crefitoNumero());
            usuario.setCrefitoUf(dto.crefitoUf());
            usuario.setTempoSessaoMinutos(dto.tempoSessaoMinutos());
        }

        // 2. Mock de telas ativas do sistema para popular a tabela associativa
        List<String> telasDoSistema = List.of("painel", "agenda", "prontuario", "config");

        // 3. Loop inteligente de permissões iniciais por cargo
        for (String telaId : telasDoSistema) {
            UsuarioPermissaoTela permissao = new UsuarioPermissaoTela();
            permissao.setUsuario(usuario);
            permissao.setTelaId(telaId);
            
            // Regra de Negócio Dinâmica
            permissao.setVisualizar(definirVisualizacaoPadrao(usuario.getFuncao(), telaId));
            permissao.setIncluir("ADMINISTRADOR".equals(usuario.getFuncao()) || ("ATENDENTE".equals(usuario.getFuncao()) && "agenda".equals(telaId)));
            permissao.setAlterar("ADMINISTRADOR".equals(usuario.getFuncao()) || ("ATENDENTE".equals(usuario.getFuncao()) && "agenda".equals(telaId)));
            
            usuario.getPermissoesTelas().add(permissao);
        }

        // 4. Salva em cascata
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioRespostaDTO(usuarioSalvo);
    }

    private boolean definirVisualizacaoPadrao(String funcao, String telaId) {
        if ("ADMINISTRADOR".equals(funcao)) return true;
        if ("FISIOTERAPEUTA".equals(funcao) && !"config".equals(telaId)) return true;
        if ("ATENDENTE".equals(funcao) && ("painel".equals(telaId) || "agenda".equals(telaId))) return true;
        return false;
    }

    public List<Usuario> listarUsuario() {
       return usuarioRepository.findAll();
    }

    public Usuario consultarPorId(Long id) {
       return usuarioRepository.findById(id).get();
    }

    public Usuario consultarUsuarioSenha(Usuario usuario) {
      return usuarioRepository.consultarUsuarioSenha(usuario.getEmail(), usuario.getSenha());
    }

    public Usuario salvar(Usuario usuario) {
        if(usuario.getId() == 0 || usuario.getId() == null){
            usuario.setAtivo(true);
            usuario.setSenha("123456");
        }


      return usuarioRepository.save(usuario);
    }

	public Usuario consultarUsuarioAdministrador() {
        return usuarioRepository.consultarUsuarioAdministrador();
	}
}
