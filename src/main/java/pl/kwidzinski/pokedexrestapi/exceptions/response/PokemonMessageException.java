package pl.kwidzinski.pokedexrestapi.exceptions.response;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class PokemonMessageException {

    private final String message;
    private final HttpStatus httpStatus;

    @DateTimeFormat(pattern="yyyy.MM.dd HH:mm:ss")
    private final LocalDateTime localDateTime;

    public PokemonMessageException(String message, HttpStatus httpStatus, LocalDateTime localDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
