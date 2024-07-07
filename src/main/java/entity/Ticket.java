package entity;


import jakarta.persistence.*;
import lombok.Data;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", insertable = true, updatable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id",  insertable = true, updatable = false)
    private Planet fromPlanet;


    @ManyToOne
    @JoinColumn(name = "to_planet_id", insertable = true, updatable = false)
    private Planet toPlanet;


    public Ticket(long id) {
        this.id = id;
    }

    public Ticket() {
    }

    public Ticket(Timestamp timestamp, Client client, Planet planetFrom, Planet planetTo) {
        this.created_at = timestamp;
        this.client = client;
        this.fromPlanet  = planetFrom;
        this.toPlanet = planetTo;
    }

    public Ticket(long id, Timestamp createdAt, Client client, Planet planetFrom, Planet planetTo) {
        this.id = id;
        this.created_at = createdAt;
        this.client = client;
        this.fromPlanet = planetFrom;
        this.toPlanet = planetTo;
    }

}
