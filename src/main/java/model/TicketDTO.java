package model;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class TicketDTO {
    private long id;
    private Timestamp createdAt;
    private long clientId;
    private String fromPlanetId;
    private String toPlanetId;
}
