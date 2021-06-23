package pl.kwidzinski.pokedexrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
        return pokemonRepo.findAll();
    }

    public Page<Pokemon> getAll(final Pageable page) {
        return pokemonRepo.findAll(page);
    }

    public Optional<Pokemon> findById(final Long id) {
        return pokemonRepo.findById(id);
    }

    public boolean existById(final Long id) {
        return pokemonRepo.existsById(id);
    }

    public Pokemon save(final Pokemon newPokemon) {
        return pokemonRepo.save(newPokemon);
    }

    public void delete(final Long id) {
        pokemonRepo.deleteById(id);
    }

    public List<Pokemon> findByType(final String type) {
        return pokemonRepo.findAll()
                .stream()
                .filter(pokemon -> pokemon.getType().toString().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Pokemon> findByName(final String name) {
        return pokemonRepo.findAll().stream()
                .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
