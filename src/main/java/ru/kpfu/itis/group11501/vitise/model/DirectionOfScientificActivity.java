package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;

/**
 * Created by Марат on 28.03.2017.
 */
@Entity
@Table(name = "direction_of_scientific_activity")
public class DirectionOfScientificActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
