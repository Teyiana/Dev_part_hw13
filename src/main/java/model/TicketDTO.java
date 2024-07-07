package model;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class TicketDTO {
    private long id;
    private Timestamp createdAt;
    private ClientDTO client;
    private PlanetDTO fromPlanet;
    private PlanetDTO toPlanet;
}
