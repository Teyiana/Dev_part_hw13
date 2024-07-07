package entity;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



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

    public Planet() {
    }

    public Planet(String planeId, String planetName) {
        this.id = planeId;
        this.name = planetName;
    }

    public Planet(String planetId) {
        this.id = planetId;
    }
}
