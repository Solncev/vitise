package ru.kpfu.itis.group11501.vitise.util;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class UserForm {

    private static final String BLANK_MESSAGE = "This field is mandatory";

    @NotBlank(message = BLANK_MESSAGE)
    private String name;

    @NotBlank(message = BLANK_MESSAGE)
    private String surname;

    private String thirdName;

    @NotBlank(message = BLANK_MESSAGE)
    @Email
    private String email;

    @NotBlank(message = BLANK_MESSAGE)
    @Size(min = 6, max = 20, message = "Password should be from 6 to 20 symbols")
    private String password;

    private String telephoneNumber;

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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
