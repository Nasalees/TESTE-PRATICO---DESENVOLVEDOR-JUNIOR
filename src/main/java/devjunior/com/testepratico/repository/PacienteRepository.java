package devjunior.com.testepratico.repository;

import devjunior.com.testepratico.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PacienteRepository extends JpaRepository<Paciente, String> {

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
