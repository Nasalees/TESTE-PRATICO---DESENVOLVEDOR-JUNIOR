package devjunior.com.testepratico.controller;


import devjunior.com.testepratico.entity.Paciente;
import devjunior.com.testepratico.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public Paciente postPaciente(@RequestBody Paciente paciente){
        pacienteService.cadastraPaciente(paciente);
        return paciente;
    }

    @GetMapping("/listar")
    public List<Paciente> listarPacientes(){
        return pacienteService.listaPacientes();
    }
}
