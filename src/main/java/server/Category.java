package server;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    @GeneratedValue
    @Id
    private int id;

    @Column(name = "name")
    private String name;
}
