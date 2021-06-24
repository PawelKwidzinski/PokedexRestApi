package pl.kwidzinski.pokedexrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kwidzinski.pokedexrestapi.exceptions.definition.EntityNotFoundException;
import pl.kwidzinski.pokedexrestapi.model.Pokemon;
import pl.kwidzinski.pokedexrestapi.repository.PokemonRepo;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final PokemonRepo pokemonRepo;

    @Autowired
    public PokemonService(final PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    public List<Pokemon> getAll() {
        List<Pokemon> pokemonList = pokemonRepo.findAll();
        if (pokemonList.isEmpty()) {
            throw new EntityNotFoundException(Pokemon.class,"<no parameters>");
        }
        return pokemonList;
    }

    public Page<Pokemon> getAll(final Pageable page) {
        Page<Pokemon> pokemonList = pokemonRepo.findAll(page);
        if (pokemonList.isEmpty()) {
            throw new EntityNotFoundException(Pokemon.class,"<no parameters>");
        }
        return pokemonList;
    }

    public Optional<Pokemon> findById(final Long id) {
        return Optional.ofNullable(pokemonRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Pokemon.class, "id = " + id)));
    }

    public Pokemon save(final Pokemon newPokemon) {
        return pokemonRepo.save(newPokemon);
    }

    public void delete(final Long id) {
        pokemonRepo.deleteById(id);
    }

    public List<Pokemon> findByType(final String type) {
        List<Pokemon> pokemonsByType = pokemonRepo.findAll()
                .stream()
                .filter(pokemon -> pokemon.getType().toString().equalsIgnoreCase(type))
                .collect(Collectors.toList());
        if (pokemonsByType.isEmpty()) {
            throw new EntityNotFoundException(Pokemon.class, "type = " + type);
        }
        return pokemonsByType;
    }

    public List<Pokemon> findByName(final String name) {
        List<Pokemon> pokemonsByName = pokemonRepo.findAll().stream()
                .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        if (pokemonsByName.isEmpty()) {
            throw new EntityNotFoundException(Pokemon.class, "name = " + name);
        }
        return pokemonsByName;
    }
}
