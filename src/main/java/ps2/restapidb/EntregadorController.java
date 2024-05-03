package ps2.restapidb;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EntregadorController {

    @Autowired
    private EntregadorRepo entregadorRepo;

    @GetMapping("/api/entregadores")
    Iterable<Entregador> getEntregadores() {
        return entregadorRepo.findAll();
    }

    @GetMapping("/api/entregadores/{id}")
    Optional<Entregador> getEntregador(@PathVariable long id) {
        return entregadorRepo.findById(id);
    }

    @PostMapping("/api/entregadores")
    Entregador createEntregador(@RequestBody Entregador e) {
        Entregador createdEntregador = entregadorRepo.save(e);
        return createdEntregador;
    }

    @PutMapping("/api/entregadores/{entregadorId}")
    Optional<Entregador> updateEntregador(@RequestBody Entregador entregadorReq, @PathVariable long entregadorId) {
        Optional<Entregador> opt = entregadorRepo.findById(entregadorId);
        if (opt.isPresent()) {
            if (entregadorReq.getCod_entregador() == entregadorId) {
                entregadorRepo.save(entregadorReq);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados do entregador com id " + entregadorId);
    }

    @DeleteMapping("/api/entregadores/{id}")
    void deleteEntregador(@PathVariable long id) {
        entregadorRepo.deleteById(id);
    }

}
