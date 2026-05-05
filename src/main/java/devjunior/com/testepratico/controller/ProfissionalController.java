package devjunior.com.testepratico.controller;


import devjunior.com.testepratico.entity.Profissional;
import devjunior.com.testepratico.service.ProfissionalService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/profissional")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    ProfissionalController(ProfissionalService profissionalService){
        this.profissionalService = profissionalService;
    }

    @PostMapping
    public Profissional postProfissional(@RequestBody Profissional profissional){
        profissionalService.cadastraProfissional(profissional);
        return profissional;
    }
}
