package model;

import lombok.Data;

@Data
public class PlanetDTO {

    private String id;
    private String name;

    public PlanetDTO(){}
    public PlanetDTO(String planetId){
        this.id = planetId;
    }
}
