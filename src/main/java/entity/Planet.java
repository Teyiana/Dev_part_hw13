package entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


@Table(name = "planet")
@Entity
@Data
public class Planet {
    @Id
    @Pattern(regexp="[A-Z0-9]")
    @NotNull
    private String id;
    @Column
    @NotNull
    private String name;

    @OneToMany(mappedBy = "from_planet_id", cascade = CascadeType.ALL)
    private List<Ticket> fromPlanets;

    @OneToMany(mappedBy = "to_planet_id", cascade = CascadeType.ALL)
    private List<Ticket> toPlanets;




    public Planet() {
    }

    public Planet(String planeId, String planetName) {
        this.id = planeId;
        this.name = planetName;
    }
}
