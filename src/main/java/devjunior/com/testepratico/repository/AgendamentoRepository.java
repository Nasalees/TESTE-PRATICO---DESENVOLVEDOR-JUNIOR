package devjunior.com.testepratico.repository;

import devjunior.com.testepratico.entity.Agendamento;
import devjunior.com.testepratico.entity.Paciente;
import devjunior.com.testepratico.entity.Profissional;
import devjunior.com.testepratico.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, String> {
    boolean existsByProfissionalAndData(Profissional profissional, LocalDateTime data);



    // List<Agendamento> findByPacienteNomeContainingIgnoreCaseOrProfissionalNomeContaininIgnoreCaseOrStatusContainingIgnoreCase(String nome);
    List<Agendamento> findByPacienteNomeCompletoContainingIgnoreCase(String paciente);
    List<Agendamento> findByProfissionalNomeContainingIgnoreCase(String profissional);
    List<Agendamento> findByStatusIgnoreCase(Status status);
}
