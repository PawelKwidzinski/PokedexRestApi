package pl.kwidzinski.pokedexrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kwidzinski.pokedexrestapi.model.Pokemon;
import pl.kwidzinski.pokedexrestapi.service.PokemonService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(final PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    public ResponseEntity<List<Pokemon>> getAll() {
        List<Pokemon> pokemonList = pokemonService.getAll();
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("")
    public ResponseEntity<List<Pokemon>> getAll(Pageable page) {
        Page<Pokemon> pokemonList = pokemonService.getAll(page);
        return ResponseEntity.ok(pokemonList.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable ("id") Long pokemonId) {
        Optional<Pokemon> optional = pokemonService.findById(pokemonId);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<Pokemon> add(@RequestBody @Valid Pokemon newPokemon) {
        Pokemon result = pokemonService.save(newPokemon);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> update(@PathVariable("id") Long id, @RequestBody Pokemon pokemonToUpdate) {
        if (pokemonService.findById(id).isPresent()) {
            pokemonToUpdate.setId(id);
            pokemonService.save(pokemonToUpdate);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> delete(@PathVariable("id") Long idToDelete) {
        if (pokemonService.existById(idToDelete)) {
            pokemonService.delete(idToDelete);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Pokemon>> getByType(@PathVariable String type) {
        List<Pokemon>pokemonsType = pokemonService.findByType(type);
        if (!pokemonsType.isEmpty()){
            return ResponseEntity.ok(pokemonsType);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Pokemon>> getByName(@PathVariable String name) {
        List<Pokemon>pokemonsName = pokemonService.findByName(name);
        if (!pokemonsName.isEmpty()){
            return ResponseEntity.ok(pokemonsName);
        }
        return ResponseEntity.notFound().build();
    }
}
