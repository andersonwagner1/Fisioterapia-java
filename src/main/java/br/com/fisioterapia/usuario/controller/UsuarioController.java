package br.com.fisioterapia.usuario.controller;


import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fisioterapia.usuario.dto.UsuarioRespostaDTO;
import br.com.fisioterapia.usuario.model.Prontuario;
import br.com.fisioterapia.usuario.model.Usuario;
import br.com.fisioterapia.usuario.service.UsuarioCadastroDTO;
import br.com.fisioterapia.usuario.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Permite o consumo direto pelo app Angular local
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para gravação de novos colaboradores com matriz de acesso automatizada
     */
    @PostMapping
    public ResponseEntity<UsuarioRespostaDTO> criarUsuario(@RequestBody @Valid UsuarioCadastroDTO payload) {
        UsuarioRespostaDTO resposta = usuarioService.cadastrarNovoUsuario(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
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