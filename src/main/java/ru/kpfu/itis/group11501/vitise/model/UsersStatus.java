package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;

@Entity
@Table(name = "users_status")
public class UsersStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usersid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "statusid")
    private Status status;

    public UsersStatus() {
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
