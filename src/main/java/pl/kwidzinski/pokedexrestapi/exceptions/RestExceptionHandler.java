package pl.kwidzinski.pokedexrestapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kwidzinski.pokedexrestapi.exceptions.definition.EntityBadRequestException;
import pl.kwidzinski.pokedexrestapi.exceptions.definition.EntityNotFoundException;
import pl.kwidzinski.pokedexrestapi.exceptions.response.PokemonMessageException;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {EntityBadRequestException.class})
    public ResponseEntity<Object> handlePokemonBadRequest(EntityBadRequestException ex) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        PokemonMessageException pokemonMessageException = new PokemonMessageException(
                ex.getMessage(),
                badRequestStatus,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(pokemonMessageException, badRequestStatus);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handlePokemonNotFound(EntityNotFoundException ex) {
        HttpStatus badRequestStatus = HttpStatus.NOT_FOUND;
        PokemonMessageException pokemonMessageException = new PokemonMessageException(
                ex.getMessage(),
                badRequestStatus,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(pokemonMessageException, badRequestStatus);
    }
}
