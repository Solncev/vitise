package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;

/**
 * Created by Марат on 28.03.2017.
 */
@Entity
@Table(name = "direction_of_scientific_activity_users")
public class DirectionOfScientificActivityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = DirectionOfScientificActivity.class)
    @JoinColumn(name = "direction_of_scientific_activity_id", referencedColumnName = "id")
    private DirectionOfScientificActivity directionOfScientificActivity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DirectionOfScientificActivity getDirectionOfScientificActivity() {
        return directionOfScientificActivity;
    }

    public void setDirectionOfScientificActivity(DirectionOfScientificActivity directionOfScientificActivity) {
        this.directionOfScientificActivity = directionOfScientificActivity;
    }
}
