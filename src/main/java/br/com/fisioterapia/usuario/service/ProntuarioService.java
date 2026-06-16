package br.com.fisioterapia.usuario.service;

import java.util.List;

import org.springframework.stereotype.Service;
import br.com.fisioterapia.usuario.model.Prontuario;
import br.com.fisioterapia.usuario.repository.ProntuarioRepository;

@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;

    // Injeção via construtor (Melhor prática que @Autowired)
    public ProntuarioService(ProntuarioRepository prontuarioRepository) {
        this.prontuarioRepository = prontuarioRepository;
    }

    public Prontuario salvarProntuario(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    public List<Prontuario> listarProntuarios() {
        return prontuarioRepository.findAll();
    }

    public Prontuario consultarPorId(Long id) {
        return prontuarioRepository.findById(id).orElse(null);
    }

    public List<Prontuario> filtro(Prontuario prontuario) {
        // Implement the filtering logic here
        return prontuarioRepository.consultarPorNomePaciente(prontuario.getNome());
    }

}
