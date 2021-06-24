package pl.kwidzinski.pokedexrestapi.exceptions.definition;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Class<?> clazz, Object... params) {
        super("Entity " + clazz.getSimpleName() + " not found using parameters: " + Arrays.stream(params)
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }
}
