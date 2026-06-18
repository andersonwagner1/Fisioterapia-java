package br.com.fisioterapia.usuario.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fisioterapia.usuario.dto.AgendamentoDto;
import br.com.fisioterapia.usuario.dto.AgendamentoRespostaDto;
import br.com.fisioterapia.usuario.model.Agendamento;
import br.com.fisioterapia.usuario.model.Prontuario;
import br.com.fisioterapia.usuario.service.AgendaService;



@RestController
@RequestMapping("/api/agendamento")
@CrossOrigin(origins = "*") // Permite o consumo direto pelo app Angular local
public class AgendamentoController {

    private final AgendaService agendaService;

    public AgendamentoController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    /**
     * Endpoint para gravação de novos colaboradores com matriz de acesso automatizada
     */
    @PostMapping("/salvar")
    public ResponseEntity<Agendamento> salvar(@RequestBody AgendamentoDto agendamento) {
        Agendamento resposta = agendaService.salva(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    
    
    @GetMapping("/listar-todos")
    public ResponseEntity<List<Agendamento>> listarTodos() {
        List<Agendamento> agendamentos = agendaService.listarTodosAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }


    @GetMapping("/consulta/{id}")
    public ResponseEntity<Agendamento> consultar(@PathVariable Long id) {
        Agendamento agendamento = agendaService.consultarPorId(id);              
        return agendamento != null ? ResponseEntity.ok(agendamento) : ResponseEntity.notFound().build();
    }

     @PostMapping("/listar-por-data")
    public ResponseEntity<List<AgendamentoRespostaDto>> listarPorDAta(@RequestBody Date dtFiltro) {
        List< AgendamentoRespostaDto> agendamento = agendaService.listarAgendaPorData(dtFiltro);       
        return agendamento != null ? ResponseEntity.ok(agendamento) : ResponseEntity.notFound().build();
    }


    @GetMapping("/listar-agendamento-em-aberto")
     public ResponseEntity<List<AgendamentoRespostaDto>> listarAgendamentoEmAberto() {
        List< AgendamentoRespostaDto> agendamento = agendaService.listarAgendamentoEmAberto();       
        return agendamento != null ? ResponseEntity.ok(agendamento) : ResponseEntity.notFound().build();
    }
}