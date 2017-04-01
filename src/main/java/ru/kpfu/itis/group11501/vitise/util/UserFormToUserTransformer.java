package ru.kpfu.itis.group11501.vitise.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.function.Function;


public class UserFormToUserTransformer implements Function<UserForm, User> {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User apply(UserForm userForm) {
        User user = new User();
        user.setName(userForm.getName());
        user.setSurname(userForm.getSurname());
        user.setEmail(userForm.getEmail());
        user.setPassword(encoder.encode(userForm.getPassword()));
        user.setThirdName(userForm.getThirdName());
        user.setTelephoneNumber(userForm.getTelephoneNumber());
        user.setPassMustBeChanged(false);
        user.setActive(null);
        user.setPhotoName("mock");
        return user;
    }

}
