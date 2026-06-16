package br.com.fisioterapia.usuario.service;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fisioterapia.usuario.dto.AgendamentoDto;
import br.com.fisioterapia.usuario.dto.AgendamentoRespostaDto;
import br.com.fisioterapia.usuario.model.Agendamento;
import br.com.fisioterapia.usuario.model.Prontuario;
import br.com.fisioterapia.usuario.repository.AgendaRepository;
import br.com.fisioterapia.usuario.repository.ProntuarioRepository;
import br.com.fisioterapia.usuario.repository.UsuarioRepository;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final UsuarioRepository usuarioRepository;
    

    // Injeção via construtor (Melhor prática que @Autowired)
    public AgendaService(UsuarioRepository usuarioRepository, AgendaRepository agendaRepository, ProntuarioRepository prontuarioRepository) {
        this.agendaRepository = agendaRepository;
        this.prontuarioRepository = prontuarioRepository;
        this.usuarioRepository =  usuarioRepository;
    }


public Date converterData(String dataHora) {
    return Date.from(
        Instant.parse(dataHora)
               .minus(0, ChronoUnit.HOURS)
    );
}
    
    public Agendamento salva(AgendamentoDto agendamentoDto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(agendamentoDto.getId());
        agendamento.setDataHoraInicio(converterData(agendamentoDto.getDtHoraInicial()));
        agendamento.setDataHoraFim(converterData(agendamentoDto.getDtHoraFinal()));
        agendamento.setObservacoes(agendamentoDto.getDsObservacaoQueixas());
        agendamento.setPaciente(prontuarioRepository.findById(agendamentoDto.getPaciente().getId()).get());
        agendamento.setStatus(agendamentoDto.getIcSituacao());
        agendamento.setTipoSessao(agendamentoDto.getTipoSessao());
        agendamento.setUsuario(usuarioRepository.findById(agendamentoDto.getUsuario().getId()).get());
        return agendaRepository.save(agendamento);
    }


    public List<Agendamento> listarTodosAgendamentos() {
        return agendaRepository.findAll();
    }


    public Agendamento consultarPorId(Long id) {
        return agendaRepository.findById(id).orElse(null);
    }


    public String obterHoraMinuto(Date data) {
    if (data == null) {
        return null;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    return sdf.format(data);
}

    public List<AgendamentoRespostaDto> listarAgendaPorData(Date dtFiltro) {

        LocalDateTime localDateTime =
        dtFiltro.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
          List<Agendamento> listaAgenda = agendaRepository.listarDataPorData(localDateTime);

         List<AgendamentoRespostaDto> listaAgendaNova = new ArrayList<>();
          for(Agendamento a : listaAgenda){
            AgendamentoRespostaDto dto = new AgendamentoRespostaDto();
            dto.setHoraInicial(obterHoraMinuto(a.getDataHoraInicio()));
            dto.setHroaFinal(obterHoraMinuto(a.getDataHoraFim()));
            dto.setIcSituacao(a.getStatus());
            dto.setId(a.getId());
            dto.setNome(a.getPaciente().getNome());
            dto.setProfissional(a.getUsuario().getNome());
            dto.setTipoSessao(a.getTipoSessao());
            listaAgendaNova.add(dto);
          }

          return listaAgendaNova;


    }



}
