package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Colleagues;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.repository.ColleaguesRepository;
import ru.kpfu.itis.group11501.vitise.repository.UserRepository;
import ru.kpfu.itis.group11501.vitise.service.ColleaguesService;


import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Наталья on 22.03.2017.
 */
@Service
public class ColleaguesServiceImpl implements ColleaguesService {

    private final ColleaguesRepository colleaguesRepository;
    private final UserRepository userRepository;

    @Autowired
    public ColleaguesServiceImpl(ColleaguesRepository colleaguesRepository, UserRepository userRepository) {
        this.colleaguesRepository = colleaguesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Colleagues getColleagues(User userOne, User userTwo) {
        try {
            return colleaguesRepository.getColleagues(userOne, userTwo);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Colleagues> getRequestSender(User sender) {
        return colleaguesRepository.getRequestSender(sender);
    }

    @Override
    public List<Colleagues> getRequestReceiver(User receiver) {
        return colleaguesRepository.getRequestReceiver(receiver);
    }

    @Override
    public List<Colleagues> getAllColleagues(User user) {
        return colleaguesRepository.getAllColleagues(user);
    }

    @Override
    public List<User> getColleagueUsers(User user) {
        List<User> colleagues = new ArrayList<>();
        for (Colleagues colleague: this.getAllColleagues(user)){
            if (user.getId() != colleague.getReceiver().getId())
                colleagues.add(colleague.getReceiver());
            else
                colleagues.add(colleague.getSender());
        }
        return colleagues;
    }

    @Override
    public int getState(Colleagues colleagues, User currentUser, User user) {
        if (colleagues == null) {
            return 0;
        } else if (Objects.equals(colleagues.getSender().getId(), currentUser.getId()) && !colleagues.isActive()) {
            return -1;
        } else if (Objects.equals(colleagues.getSender().getId(), user.getId()) && !colleagues.isActive()) {
            return -2;
        } else {
            return 1;
        }
    }

    @Override
    public void add(User currentUser, User user) {
        Colleagues colleagues = new Colleagues();
        colleagues.setSender(currentUser);
        colleagues.setReceiver(user);
        colleagues.setActive(false);
        colleaguesRepository.save(colleagues);
    }

    @Override
    public void delete(Long id) {
        colleaguesRepository.delete(id);
    }

    @Override
    public void approve(Long id) {
        Colleagues colleagues = colleaguesRepository.findOne(id);
        colleagues.setActive(true);
        colleaguesRepository.save(colleagues);
    }
}
