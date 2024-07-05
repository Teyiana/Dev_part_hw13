package entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;



@Entity
@Table(name = "ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private Timestamp created_at;
    @Column
    @NotNull
    private Long client_id;
    @Column
    @NotNull
    private String from_planet_id;
    @Column
    @NotNull
    private String to_planet_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    @ToString.Exclude
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id",  insertable = false, updatable = false)
    @ToString.Exclude
    private Planet fromPlanet;


    @ManyToOne
    @JoinColumn(name = "to_planet_id", insertable = false, updatable = false)
    @ToString.Exclude
    private Planet toPlanet;


    public Ticket(long id) {
        this.id = id;
    }

    public Ticket() {
    }

    public Ticket(Timestamp timestamp, long clientId, String planetFrom, String planetTo) {
        this.created_at = timestamp;
        this.client_id = clientId;
        this.from_planet_id = planetFrom;
        this.to_planet_id = planetTo;
    }

    public Ticket(long id, Timestamp createdAt, long clientId, String fromPlanId, String toPlanId) {
        this.id = id;
        this.created_at = createdAt;
        this.client_id = clientId;
        this.from_planet_id = fromPlanId;
        this.to_planet_id =  toPlanId;
    }
}
