package devjunior.com.testepratico.controller;


import devjunior.com.testepratico.entity.Agendamento;
import devjunior.com.testepratico.entity.Status;
import devjunior.com.testepratico.service.AgendamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    AgendamentoController(AgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public Agendamento postAgendamento(@RequestBody Agendamento agendamento){
        return agendamentoService.criarAgendamento(agendamento);
    }

    @PatchMapping("/cancelar/{id}")
    public void cancelarAgendamento(
            @RequestBody Agendamento agendamentoAtualizado,
            @PathVariable String id){

        agendamentoService.cancelamento(id, agendamentoAtualizado);
    }

//    @GetMapping("/geral/{geral}")
//    public List<Agendamento> listaGeral(@PathVariable String geral){
//        return agendamentoService.filtroGeral(geral);
//    }

    @GetMapping("/paciente/{paciente}")
    public List<Agendamento> listaPaciente(@PathVariable String paciente){
        return agendamentoService.filtroPaciente(paciente);
    }

    @GetMapping("/profissional/{profissional}")
    public List<Agendamento> listaProfissional(@PathVariable String profissional){
        return agendamentoService.filtroProfissional(profissional);
    }

    @GetMapping("/status/{status}")
    public List<Agendamento> listaStatus(@PathVariable Status status){
        return agendamentoService.filtroStatus(status);
    }
}
