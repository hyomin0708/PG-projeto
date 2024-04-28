package ps2.restapidb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EntregadorController {

    @Autowired
    private EntregadorRepo entregadorRepository;

    @GetMapping
    public Iterable<Entregador> getAllEntregadores() {
        return entregadorRepository.findAll();
    }


    @GetMapping("/{cod_entregador}")
    public ResponseEntity<Entregador> getEntregadorById(@PathVariable("cod_entregador") long cod_entregador) {
        Optional<Entregador> entregadorOptional = entregadorRepository.findById(cod_entregador);
        return entregadorOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Entregador> createEntregador(@RequestBody Entregador entregador) {
        Entregador savedEntregador = entregadorRepository.save(entregador);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntregador);
    }

    @PutMapping("/{cod_entregador}")
    public ResponseEntity<Entregador> updateEntregador(@PathVariable("cod_entregador") long cod_entregador, @RequestBody Entregador updatedEntregador) {
        Optional<Entregador> existingEntregadorOptional = entregadorRepository.findById(cod_entregador);
        if (existingEntregadorOptional.isPresent()) {
            Entregador existingEntregador = existingEntregadorOptional.get();
            existingEntregador.setNome(updatedEntregador.getNome());
            existingEntregador.setTelefone(updatedEntregador.getTelefone());
            existingEntregador.setPontuacao(updatedEntregador.getPontuacao());
            existingEntregador.setSituacaoEntrega(updatedEntregador.getSituacaoEntrega());
            entregadorRepository.save(existingEntregador);
            return ResponseEntity.ok(existingEntregador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cod_entregador}")
    public ResponseEntity<Void> deleteEntregador(@PathVariable("cod_entregador") long cod_entregador) {
        if (entregadorRepository.existsById(cod_entregador)) {
            entregadorRepository.deleteById(cod_entregador);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
