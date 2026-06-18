package br.com.fisioterapia.usuario.controller;


import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fisioterapia.usuario.dto.UsuarioRespostaDTO;
import br.com.fisioterapia.usuario.model.Usuario;
import br.com.fisioterapia.usuario.service.TokenService;
import br.com.fisioterapia.usuario.service.UsuarioCadastroDTO;
import br.com.fisioterapia.usuario.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Permite o consumo direto pelo app Angular local
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    public UsuarioController(UsuarioService usuarioService, TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.tokenService= tokenService;
    }

    /**
     * Endpoint para gravação de novos colaboradores com matriz de acesso automatizada
     */
    @PostMapping
    public ResponseEntity<UsuarioRespostaDTO> criarUsuario(@RequestBody @Valid UsuarioCadastroDTO payload) {
        UsuarioRespostaDTO resposta = usuarioService.cadastrarNovoUsuario(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }


     @PostMapping("/autenticacao")
    public ResponseEntity< Map<String, String>> autenticacao(@RequestBody Usuario usuario) {


        if(usuario.getEmail().equals("ADM")){
              Usuario usuarioaDM = usuarioService.consultarUsuarioAdministrador();
              //primeiro acesso
              if(usuarioaDM == null){
                usuarioaDM = new Usuario();
                usuarioaDM.setNome("Administrador");
                usuarioaDM.setEmail("ADM");
                usuarioaDM.setSenha("123");
                usuario = usuarioService.salvar(usuarioaDM);
              }                           
        }

        Usuario prontuarios = usuarioService.consultarUsuarioSenha(usuario);
        String token = null;
        if(prontuarios != null){
            token = this.tokenService.gerarToken(prontuarios);
        }

        Map<String, String> resposta = new HashMap<>();
        resposta.put("token", token);

        return ResponseEntity.ok(resposta);

        
        
    }

     @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        usuario = usuarioService.salvar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> prontuarios = usuarioService.listarUsuario();
        return ResponseEntity.ok(prontuarios);
    }


    @GetMapping("/consulta/{id}")
    public ResponseEntity<Usuario> consultar(@PathVariable Long id) {
        Usuario usuario = usuarioService.consultarPorId(id);              
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }
}