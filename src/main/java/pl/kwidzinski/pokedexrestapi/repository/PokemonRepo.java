package pl.kwidzinski.pokedexrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kwidzinski.pokedexrestapi.model.Pokemon;

@Repository
public interface PokemonRepo extends JpaRepository<Pokemon, Long> {
}
