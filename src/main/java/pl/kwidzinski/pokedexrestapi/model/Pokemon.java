package pl.kwidzinski.pokedexrestapi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;

    @NotNull(message = "Name cannot be not null")
    @Size(min = 2)
    private String name;
    @NotNull(message = "Name cannot be not null")
    @Size(min = 2)
    private String species;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type cannot be null")
    private Type type;

    public Pokemon() {
    }

    public Pokemon(final String name, final String species, final Type type) {
        this.name = name;
        this.species = species;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(final String species) {
        this.species = species;
    }


    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }
}
