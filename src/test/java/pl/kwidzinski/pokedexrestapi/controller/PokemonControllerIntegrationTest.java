package pl.kwidzinski.pokedexrestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kwidzinski.pokedexrestapi.model.Pokemon;
import pl.kwidzinski.pokedexrestapi.model.Type;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PokemonControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void should_return_404_when_get() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/pokemons/20"))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andReturn();
        final String actual = Objects.requireNonNull(mvcResult.getResolvedException()).getMessage();
        Assertions.assertEquals("Entity Pokemon not found using parameters: id = 20", actual);
    }

    @Test
    void should_return_selected_pokemon() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/pokemons"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        final Pokemon[] pokemons = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Pokemon[].class);
        Assertions.assertEquals("Pikachu", pokemons[0].getName());
    }

    @Test
    void should_return_correct_pokemon_value() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pokemons/4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Squirtle")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.species", Is.is("Turtle")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", Is.is("WATER")))
                .andReturn();
    }

    @Test
    void should_return_pokemon_by_name_value() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pokemons/search").param("name","charmander"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.equalTo("Charmander")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].species", Matchers.equalTo("Lizard")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type", Matchers.equalTo("FIRE")))
                .andReturn();
    }

    @Test
    void should_return_pokemons_by_types_value() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pokemons/filter").param("type","electric"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.equalTo("Pikachu")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].species", Matchers.equalTo("Mouse")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type", Matchers.equalTo("ELECTRIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.equalTo(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.equalTo("Zapdos")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].species", Matchers.equalTo("Electric")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].type", Matchers.equalTo("ELECTRIC")))
                .andReturn();
    }

    @Test
    @Rollback(value = false)
    void should_add_new_pokemon() throws Exception {
        Pokemon pokemonToSave = new Pokemon("foo", "bar", Type.ELECTRIC);

        mockMvc.perform(MockMvcRequestBuilders.post("/pokemons")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(pokemonToSave))
        )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Rollback(value = false)
    void should_add_update_pokemon() throws Exception {
        Pokemon pokemonToUpdate = new Pokemon("Pikachu", "Small Mouse", Type.ELECTRIC);

        mockMvc.perform(MockMvcRequestBuilders.put("/pokemons/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(pokemonToUpdate))
        )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Rollback(value = false)
    void should_delete_selected_pokemon() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/pokemons/3")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }
}
