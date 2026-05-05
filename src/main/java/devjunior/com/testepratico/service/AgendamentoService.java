package devjunior.com.testepratico.service;


import devjunior.com.testepratico.entity.Agendamento;
import devjunior.com.testepratico.entity.Status;
import devjunior.com.testepratico.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final PacienteService pacienteService;
    private final ProfissionalService profissionalService;

   public AgendamentoService(AgendamentoRepository agendamentoRepository, PacienteService pacienteService, ProfissionalService profissionalService ){
        this.agendamentoRepository = agendamentoRepository;
        this.pacienteService = pacienteService;
        this.profissionalService = profissionalService;
    }

    public Agendamento criarAgendamento(Agendamento agendamento){

        if (agendamento == null) {
            throw new RuntimeException("Agendamento está vazio");
        }

        if (agendamento.getData() == null) {
            throw new RuntimeException("Data é obrigatória");
        }

        if (agendamento.getData().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Data para agendamento inválida. A data precisa ser futura");
        }

        if (agendamento.getStatus() == null) {
            throw new RuntimeException("Status é obrigatório");
        }

        if (agendamento.getTipoAgendamento() == null) {
            throw new RuntimeException("Tipo de agendamento é obrigatório");
        }

        if (agendamento.getProfissional() == null || agendamento.getProfissional().getId() == null) {
            throw new RuntimeException("Profissional é obrigatório");
        }

        if (agendamento.getPaciente() == null || agendamento.getPaciente().getId() == null) {
            throw new RuntimeException("Paciente é obrigatório");
        }

        if(agendamentoRepository.existsByProfissionalAndData(agendamento.getProfissional(), agendamento.getData())){
            throw new RuntimeException("Data não disponível");
        }

        if(agendamentoRepository.existsByPacienteAndData(agendamento.getPaciente(), agendamento.getData())){
            throw new RuntimeException("Paciente já tem outra consulta com esse mesmo dia e horário");
        }

        if(!pacienteService.existePorId( agendamento.getPaciente().getId())){
            throw new RuntimeException("Cliente não cadastrado");
        }

        if(!profissionalService.existePorId( agendamento.getProfissional().getId())){
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

    public void cancelamento(String agendamentoId, Agendamento agendamentoAtualizado){

        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
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
    public List<Agendamento> filtroPaciente(String pacienteNome){

        List<Agendamento> agendamentos = agendamentoRepository
                        .findByPacienteNomeCompletoContainingIgnoreCase(pacienteNome);
        return agendamentos;
    }

    public List<Agendamento> filtroProfissional(String profissionalNome){

        List<Agendamento> agendamentos = agendamentoRepository.findByProfissionalNomeContainingIgnoreCase(profissionalNome);
        return agendamentos;
    }

    public List<Agendamento> filtroStatus(Status status){

        List<Agendamento> agendamentos = agendamentoRepository.findByStatus(status);
        return agendamentos;
    }
}
