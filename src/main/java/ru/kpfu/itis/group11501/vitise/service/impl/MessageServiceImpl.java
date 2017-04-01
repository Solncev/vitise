package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Message;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.repository.MessageRepository;
import ru.kpfu.itis.group11501.vitise.repository.UserRepository;
import ru.kpfu.itis.group11501.vitise.service.MessageService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Марат on 25.03.2017.
 */
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, EntityManager entityManager) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }


    @Override
    public List<Message> findAll(Long recipientId, Long senderId) {
        User sender = userRepository.findOne(senderId);
        User recipient = userRepository.findOne(recipientId);
        return messageRepository.findAll(sender, recipient);
    }

    @Override
    public Set<Long> findAllInterlocutors(User user) {
        Set<Long> usersId = new HashSet<>();
        List<User> recipientList = entityManager.createQuery("select distinct m.recipient from Message m where m.sender = :u", User.class)
                .setParameter("u", user)
                .getResultList();
        List<User> senderList = entityManager.createQuery("select distinct m.sender from Message m where m.recipient = :u", User.class)
                .setParameter("u", user)
                .getResultList();
        for (User u : recipientList) {
            usersId.add(u.getId());
        }
        for (User u : senderList) {
            usersId.add(u.getId());
        }
        return usersId;
    }

    @Override
    public void add(Message message) {
        messageRepository.save(message);
    }
}
