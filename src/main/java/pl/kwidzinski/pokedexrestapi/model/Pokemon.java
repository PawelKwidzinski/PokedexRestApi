package pl.kwidzinski.pokedexrestapi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;

    @NotBlank(message = "Name cannot be not empty")
    private String name;
    @NotBlank(message = "Name cannot be not empty")
    private String species;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Pokemon() {
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
