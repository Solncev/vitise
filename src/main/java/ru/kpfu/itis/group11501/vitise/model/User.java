package ru.kpfu.itis.group11501.vitise.model;

import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(name = "third_name")
    private String thirdName;

    @Column(name = "is_active")
    private Boolean isActive;

    private String email;

    private String password;

    @Column(name = "photo_name")
    private String photoName;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    private String description;

    @Column(name = "pass_must_be_changed")
    private boolean passMustBeChanged;

    @Transient
    private Group group;

    @Transient
    private StatusName status;

    public User() {
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPassMustBeChanged() {
        return passMustBeChanged;
    }

    public void setPassMustBeChanged(boolean passMustBeChanged) {
        this.passMustBeChanged = passMustBeChanged;
    }

    public boolean equals(Object obj) {
        return obj instanceof User && this.getId().equals(((User) obj).getId());
    }

    public String getFullName() {
        return this.surname + " " + this.name + " " + this.thirdName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public StatusName getStatusName() {
        return status;
    }

    public void setStatusName(StatusName status) {
        this.status = status;
    }
}
