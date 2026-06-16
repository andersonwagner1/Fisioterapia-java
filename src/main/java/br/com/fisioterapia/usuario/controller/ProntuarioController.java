package br.com.fisioterapia.usuario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fisioterapia.usuario.model.Prontuario;
import br.com.fisioterapia.usuario.service.ProntuarioService;


@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    /**
     * Endpoint para gravação de novos colaboradores com matriz de acesso automatizada
     */
    @PostMapping("/salvar")
    public ResponseEntity<Prontuario> salvar(@RequestBody Prontuario prontuario) {
        Prontuario resposta = prontuarioService.salvarProntuario(prontuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Prontuario>> listarTodos() {
        List<Prontuario> prontuarios = prontuarioService.listarProntuarios();
        return ResponseEntity.ok(prontuarios);
    }


    @GetMapping("/consulta/{id}")
    public ResponseEntity<Prontuario> consultar(@PathVariable Long id) {
        Prontuario prontuario = prontuarioService.consultarPorId(id);              
        return prontuario != null ? ResponseEntity.ok(prontuario) : ResponseEntity.notFound().build();
    }

     @PostMapping("/filtro")
    public ResponseEntity<List<Prontuario>> consultar(@RequestBody Prontuario prontuario) {
        List<Prontuario> prontuarios =  prontuarioService.filtro(prontuario);  
        return ResponseEntity.ok(prontuarios);
    }
}


