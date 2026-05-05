package devjunior.com.testepratico.service;

import devjunior.com.testepratico.entity.Paciente;
import devjunior.com.testepratico.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    PacienteService(PacienteRepository repository){
        this.repository = repository;
    }

    public Paciente cadastraPaciente(Paciente paciente){
        if(repository.existsByEmail(paciente.getEmail()) || repository.existsByCpf(paciente.getCpf())){
            throw new RuntimeException("Email ou CPF já cadastrado");
        }
           return repository.save(paciente);
    }

    public List<Paciente> listaPacientes(){
        List<Paciente> pacientes = repository.findAll();

        if (pacientes.isEmpty()){
            throw new RuntimeException("Nenhum paciente cadastrado");
        }
        return pacientes;
    }
}
