package br.com.itengine.score.entity;//
// This file was generated by the JPA Modeler
//

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Role implements Serializable {

    private String name;

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")
    private Long id;

    public Role() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
