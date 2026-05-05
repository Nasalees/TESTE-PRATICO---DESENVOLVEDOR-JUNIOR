package devjunior.com.testepratico.service;


import devjunior.com.testepratico.entity.Agendamento;
import devjunior.com.testepratico.entity.Status;
import devjunior.com.testepratico.repository.AgendamentoRepository;
import devjunior.com.testepratico.repository.PacienteRepository;
import devjunior.com.testepratico.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final PacienteRepository pacienteRepository;

    AgendamentoService(AgendamentoRepository agendamentoRepository, ProfissionalRepository profissionalRepository, PacienteRepository pacienteRepository ){
        this.agendamentoRepository = agendamentoRepository;
        this.profissionalRepository = profissionalRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public Agendamento criarAgendamento(Agendamento agendamento){

        if (agendamento.getData().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Data para agendamento inválida. A data precisa ser futura");
        }

        if(agendamentoRepository.existsByProfissionalAndData(agendamento.getProfissional(), agendamento.getData())){
            throw new RuntimeException("Data não disponível");
        }

        if(!pacienteRepository.existsById( agendamento.getPaciente().getId())){
            throw new RuntimeException("Cliente não cadastrado");
        }

        if(!profissionalRepository.existsById( agendamento.getProfissional().getId())){
            throw new RuntimeException("Profissional não cadastrado");
        }

        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listaAgendamentos(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();

        if (agendamentos.isEmpty()){
            throw new RuntimeException("Nenhum agendamento cadastrado");
        }
        return agendamentos;
    }

    public void cancelamento(String id, Agendamento agendamentoAtualizado){

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        if(agendamentoAtualizado.getMotivoCancelamento() == null ||
                agendamentoAtualizado.getMotivoCancelamento().isBlank()){

            throw new RuntimeException(
                    "O motivo do cancelamento deve ser informado");
        }

        agendamento.setMotivoCancelamento(agendamentoAtualizado.getMotivoCancelamento());

        agendamento.setStatus(Status.CANCELADO);

        agendamentoRepository.save(agendamento);
    }

//    public List<Agendamento> filtroGeral(String geral){
//        return repository.findByPacienteNomeContainingIgnoreCaseOrProfissionalNomeContaininIgnoreCaseOrStatusContainingIgnoreCase(geral);
//    }
    public List<Agendamento> filtroPaciente(String paciente){

        List<Agendamento> agendamentos = agendamentoRepository
                        .findByPacienteNomeCompletoContainingIgnoreCase(paciente);

        if(agendamentos.isEmpty()){
            throw new RuntimeException(
                    "Nenhum paciente encontrado com o nome: " + paciente);
        }

        return agendamentos;
    }

    public List<Agendamento> filtroProfissional(String profissional){

        List<Agendamento> agendamentos = agendamentoRepository.findByProfissionalNomeContainingIgnoreCase(profissional);

        if(agendamentos.isEmpty()){
            throw new RuntimeException(
                    "Nenhum agendamento encontrado com para o profissional: " + profissional);
        }

        return agendamentos;
    }

    public List<Agendamento> filtroStatus(Status status){

        List<Agendamento> agendamentos = agendamentoRepository.findByStatusIgnoreCase(status);

        if(agendamentos.isEmpty()){
            throw new RuntimeException(
                    "Nenhum agendamento encontrado com o status: " + status);
        }

        return agendamentos;
    }
}
