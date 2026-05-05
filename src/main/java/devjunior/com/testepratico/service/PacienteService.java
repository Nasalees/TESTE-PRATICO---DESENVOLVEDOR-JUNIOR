package devjunior.com.testepratico.service;

import devjunior.com.testepratico.entity.Paciente;
import devjunior.com.testepratico.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository){
        this.repository = repository;
    }

    public Paciente cadastraPaciente(Paciente paciente){
        if(repository.existsByEmail(paciente.getEmail())){
            throw new RuntimeException("Email já cadastrado");
        }

        if(repository.existsByCpf(paciente.getCpf())){
            throw new RuntimeException("CPF já cadastrado");
        }
           return repository.save(paciente);
    }

    public List<Paciente> listaPacientes(){
        List<Paciente> pacientes = repository.findAll();

        return pacientes;
    }

    public boolean existePorId(String id) {
        return repository.existsById(id);
    }
}
