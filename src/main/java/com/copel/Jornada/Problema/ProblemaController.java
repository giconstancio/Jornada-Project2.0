package com.copel.Jornada.Problema;

import org.springframework.http.ResponseEntity;
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
    public List<Problema> listarTodos() {
        return problemaService.listarTodos();
    }

    @GetMapping("/getById/{id}")
    public Problema buscarPorId(@PathVariable Long id) {
        return problemaService.buscarPorId(id);
    }

    @PostMapping("/create")
    public Problema criar(@RequestBody Problema problema) {
        return problemaService.criar(problema);
    }

    @PutMapping("/updateProblema/{id}")
    public Problema atualizar(@PathVariable Long id, @RequestBody Problema problema) {
        return problemaService.atualizar(id, problema);
    }

    @DeleteMapping("/delete/{id}")
    public void deletar(@PathVariable Long id) {
        problemaService.deletar(id);
    }
}