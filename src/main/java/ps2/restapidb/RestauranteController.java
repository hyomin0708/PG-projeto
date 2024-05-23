package ps2.restapidb;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepo restauranteRepo;

    @GetMapping
    public Iterable<Restaurante> getAllRestaurantes() {
        return restauranteRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Restaurante> getRestauranteById(@PathVariable Long id) {
        return restauranteRepo.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante createRestaurante(@RequestBody Restaurante restaurante) {
        return restauranteRepo.save(restaurante);
    }

    @PutMapping("/{id}")
    public Optional<Restaurante> updateRestaurante(@RequestBody Restaurante restaurante, @PathVariable Long id) {
        if (!restauranteRepo.existsById(id)) {
            throw new ResourceNotFoundException("Restaurante not found with id: " + id);
        }
        restaurante.setId(id);
        return Optional.of(restauranteRepo.save(restaurante));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurante(@PathVariable Long id) {
        if (!restauranteRepo.existsById(id)) {
            throw new ResourceNotFoundException("Restaurante not found with id: " + id);
        }
        restauranteRepo.deleteById(id);
    }
}
