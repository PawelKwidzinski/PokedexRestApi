package pl.kwidzinski.pokedexrestapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kwidzinski.pokedexrestapi.model.Pokemon;
import pl.kwidzinski.pokedexrestapi.model.Type;
import pl.kwidzinski.pokedexrestapi.repository.PokemonRepo;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private PokemonRepo pokemonRepo;

    @InjectMocks
    private PokemonService pokemonService;

    private List<Pokemon> testPokemonList() {
        return Arrays.asList(
                new Pokemon("Pokemon 1", "Species 1", Type.FLYING),
                new Pokemon("Pokemon 2", "Species 2", Type.ELECTRIC),
                new Pokemon("Pokemon 3", "Species 3", Type.DRAGON),
                new Pokemon("Pokemon 4", "Species 4", Type.FLYING));
    }

    @Test
    void should_create_given_pokemon() {
        //given
        Pokemon pokemonToSave = new Pokemon("Pikachu", "Mouse", Type.ELECTRIC);

        //when
        when(pokemonRepo.save(any(Pokemon.class))).thenReturn(pokemonToSave);

        //then
        Pokemon save = pokemonService.save(pokemonToSave);
        Assertions.assertSame(pokemonToSave, save);
        verify(pokemonRepo).save(pokemonToSave);
    }

    @Test
    void should_return_all_pokemons() {
        // given
        List<Pokemon> testPokemons = testPokemonList();
        given(pokemonRepo.findAll()).willReturn(testPokemons);

        // when
        List<Pokemon> expectedPokemons = pokemonService.getAll();

        // then
        assertEquals(expectedPokemons, testPokemons);
        verify(pokemonRepo).findAll();
    }

    @Test
    void should_find_pokemon_by_id() {
        // given
        Pokemon testPokemon1 = new Pokemon("Pokemon 5", "Species 5", Type.ELECTRIC);
        testPokemon1.setId(5L);

        //when
        when(pokemonRepo.findById(anyLong())).thenReturn(Optional.of(testPokemon1));

        // then
        Optional<Pokemon> actual = pokemonService.findById(5L);
        assertEquals(5L, actual.get().getId());
        verify(pokemonRepo).findById(anyLong());
    }

    @Test
    void should_delete_pokemon_by_id() {
        // given
        Pokemon testPokemon2 = new Pokemon("Pokemon 6", "Species 6", Type.DRAGON);
        testPokemon2.setId(6L);

        //when
        pokemonService.delete(testPokemon2.getId());

        // then
        verify(pokemonRepo, times(1)).deleteById(anyLong());
    }

    @Test
    void should_find_pokemon_by_name() {
        // given
        List<Pokemon> testPokemons = testPokemonList();
        given(pokemonRepo.findAll()).willReturn(testPokemons);
        String name = "Pokemon 2";

        // when
        List<Pokemon> byName = pokemonService.findByName(name);

        // then
        assertEquals(byName.size(), 1);
    }

    @Test
    void should_find_pokemon_by_type() {
        // given
        List<Pokemon> testPokemons = testPokemonList();
        given(pokemonRepo.findAll()).willReturn(testPokemons);
        String type = "flying";

        // when
        List<Pokemon> byType = pokemonService.findByType(type);

        // then
        assertThat(byType).size().isGreaterThan(1);
    }
}
