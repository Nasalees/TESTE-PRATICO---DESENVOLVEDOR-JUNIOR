package devjunior.com.testepratico.service;

import devjunior.com.testepratico.entity.Profissional;
import devjunior.com.testepratico.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfissionalService {

    private final ProfissionalRepository repository;

    public ProfissionalService(ProfissionalRepository repository){
        this.repository = repository;
    }

    public Profissional cadastraProfissional(Profissional profissional){
        return repository.save(profissional);
    }

    public boolean existePorId(String id) {
        return repository.existsById(id);
    }
}
