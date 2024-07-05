package entity;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Table(name = "client")
@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;


    public Client() {
    }

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
        tickets = new ArrayList<>();
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(long id) {
        this.id = id;
    }
}
