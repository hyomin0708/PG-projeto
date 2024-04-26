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
        return EntregadorRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entregador> getEntregadorById(@PathVariable("id") long id) {
        Optional<Entregador> entregadorOptional = entregadorRepository.findById(id);
        return entregadorOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Entregador> createEntregador(@RequestBody Entregador entregador) {
        Entregador savedEntregador = entregadorRepository.save(entregador);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntregador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entregador> updateEntregador(@PathVariable("id") long id, @RequestBody Entregador updatedEntregador) {
        Optional<Entregador> existingEntregadorOptional = entregadorRepository.findById(id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntregador(@PathVariable("id") long id) {
        if (entregadorRepository.existsById(id)) {
            entregadorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
