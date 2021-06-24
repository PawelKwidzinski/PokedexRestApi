package pl.kwidzinski.pokedexrestapi.exceptions.definition;

public class EntityBadRequestException extends RuntimeException{

    public EntityBadRequestException(final Long id) {
        super("Could not find Entity Pokemon with id: " + id);
    }
}
