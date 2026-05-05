package service;

import devjunior.com.testepratico.service.PacienteService;
import org.junit.jupiter.api.Test;
import devjunior.com.testepratico.entity.Paciente;
import devjunior.com.testepratico.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void deveCadastrarPacienteComSucesso() {

        Paciente paciente = new Paciente();

        paciente.setNomeCompleto("Nathalia Sales");
        paciente.setCpf("12345678901");
        paciente.setEmail("nathalia@email.com");

        when(pacienteRepository.save(paciente))
                .thenReturn(paciente);

        Paciente resultado =
                pacienteService.cadastraPaciente(paciente);

        assertEquals(
                "Nathalia Sales",
                resultado.getNomeCompleto()
        );

        assertEquals(
                "12345678901",
                resultado.getCpf()
        );

        assertEquals(
                "nathalia@email.com",
                resultado.getEmail()
        );
    }
}
