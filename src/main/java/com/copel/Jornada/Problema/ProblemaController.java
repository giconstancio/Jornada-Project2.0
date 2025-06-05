package com.copel.Jornada.Problema;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/problemas")
public class ProblemaController {

    private final ProblemaService problemaService;

    public ProblemaController(ProblemaService problemaService) {
        this.problemaService = problemaService;
    }

    @GetMapping("/getAll")
    public List<Problema> listarProblemas() {
        return problemaService.listarTodos();
    }
}
