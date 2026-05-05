package devjunior.com.testepratico.repository;

import devjunior.com.testepratico.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, String> {
}
