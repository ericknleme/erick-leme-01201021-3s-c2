package br.com.bandtec.ericklemeac2.controle;

import br.com.bandtec.ericklemeac2.dominio.Lutador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.bandtec.ericklemeac2.repositorio.LutadorRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @PostMapping
    public ResponseEntity postLutadores(@RequestBody @Valid Lutador novoLutador) {
        repository.save(novoLutador);
        return ResponseEntity.status(201).build();
//        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public ResponseEntity getLutadores() {
        return ResponseEntity.status(200).body(repository.findAllOrderByForcaGolpe());
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getVivos(){
        return ResponseEntity.ok(repository.contarVivos());
    }

    @GetMapping("/mortos")
    public ResponseEntity getMortos() {
        return ResponseEntity.ok(repository.contarMortos());
    }

    @PostMapping("/{id}/concentrar")
    public ResponseEntity cadastrarUsuario(@PathVariable Integer id){
        if(repository.existsById(id)) {
            repository.findById(id).ifPresent(lutador1 -> lutador1.setConcentracoesRealizadas(
                    lutador1.getConcentracoesRealizadas()+1));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

