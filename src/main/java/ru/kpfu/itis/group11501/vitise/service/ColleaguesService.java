package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.Colleagues;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Created by Наталья on 22.03.2017.
 */
public interface ColleaguesService {

    //получаем одну связь, flag=any
    Colleagues getColleagues(User userOne, User userTwo);

    //все мои запросы, flag = false
    List<Colleagues> getRequestSender(User sender);

    //заявки отправленные мне, flag = false
    List<Colleagues> getRequestReceiver(User receiver);

    //все мои друзья, я или sender или receiver, flag = true
    List<Colleagues> getAllColleagues(User user);

    List<User> getColleagueUsers(User user);

    int getRequestsCount(User user);

    //1-друзья, 0 - не друзья, -1 - отправил заявку уже, -2 - подтвердить
    int getState(Colleagues colleagues, User userOne, User userTwo);

    void add(User currentUser, User user);

    void delete(Long id);

    void approve(Long id);
}
