package br.com.fisioterapia.usuario.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.com.fisioterapia.usuario.model.EvolucaoClinica;
import br.com.fisioterapia.usuario.repository.EvolucaoClinicaRepository;
import br.com.fisioterapia.usuario.repository.ProntuarioRepository;
import br.com.fisioterapia.usuario.repository.UsuarioRepository;


@Service
public class EvolucaoClinicaService {

    private final EvolucaoClinicaRepository evolucaoClinicaRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final UsuarioRepository usuarioRespository;
  
    

    // Injeção via construtor (Melhor prática que @Autowired)
    public EvolucaoClinicaService(EvolucaoClinicaRepository evolucaoClinicaRepository, ProntuarioRepository prontuarioRepository, UsuarioRepository usuarioRespository) {
        this.evolucaoClinicaRepository = evolucaoClinicaRepository;
        this.prontuarioRepository = prontuarioRepository;
        this.usuarioRespository = usuarioRespository;

    }

    public EvolucaoClinica salvar(EvolucaoClinica evolucaoClinica){
        evolucaoClinica.setFisioterapeuta(usuarioRespository.findById(evolucaoClinica.getFisioterapeuta().getId()).get());
        evolucaoClinica.setProntuario(prontuarioRepository.findById(evolucaoClinica.getProntuario().getId()).get());

        return evolucaoClinicaRepository.save(evolucaoClinica);
    }

    public List<EvolucaoClinica> listarEvolucaoPorPaciente(long id){
        return evolucaoClinicaRepository.listarEvolucaocaPorPaciente(id);
    }

    public EvolucaoClinica consultarPorId(Long id) {
        return evolucaoClinicaRepository.findById(id).get();
    }
}
