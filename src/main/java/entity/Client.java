package entity;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;


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

    public Client() {
    }

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(long id) {
        this.id = id;
    }
}
