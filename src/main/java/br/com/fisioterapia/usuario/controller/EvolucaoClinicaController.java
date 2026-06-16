package br.com.fisioterapia.usuario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fisioterapia.usuario.model.EvolucaoClinica;
import br.com.fisioterapia.usuario.service.EvolucaoClinicaService;



@RestController
@RequestMapping("/api/evolucao")
@CrossOrigin(origins = "*") // Permite o consumo direto pelo app Angular local
public class EvolucaoClinicaController {

    private final EvolucaoClinicaService evolucaoClinicaService;

    public EvolucaoClinicaController(EvolucaoClinicaService evolucaoClinicaService) {
        this.evolucaoClinicaService = evolucaoClinicaService;
    }

    /**
     * Endpoint para gravação de novos colaboradores com matriz de acesso automatizada
     */
    @PostMapping("/salvar")
    public ResponseEntity<EvolucaoClinica> salvar(@RequestBody EvolucaoClinica evolucao) {
        EvolucaoClinica resposta = evolucaoClinicaService.salvar(evolucao);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    
    
    @GetMapping("/listar-todos")
    public ResponseEntity<List<EvolucaoClinica>> listarTodos() {
        //List<EvolucaoClinica> agendamentos = evolucaoClinicaService.listarTodosAgendamentos();
        //return ResponseEntity.ok(agendamentos);
        return null;
    }

    @GetMapping("/listar-por-paciente/{id}")
    public ResponseEntity<List<EvolucaoClinica>> listarPorPaciente(@PathVariable Long id) {
        List<EvolucaoClinica> evolucaoClinica = evolucaoClinicaService.listarEvolucaoPorPaciente(id);
        return ResponseEntity.ok(evolucaoClinica);
    }



    @GetMapping("/consulta/{id}")
    public ResponseEntity<EvolucaoClinica> consultar(@PathVariable Long id) {
        EvolucaoClinica evolucaoClinica = evolucaoClinicaService.consultarPorId(id);              
        return evolucaoClinica != null ? ResponseEntity.ok(evolucaoClinica) : ResponseEntity.notFound().build();
    }



    
}