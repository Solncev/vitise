package ru.kpfu.itis.group11501.vitise.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.group11501.vitise.model.conversation.Conversation;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessage;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessage_;
import ru.kpfu.itis.group11501.vitise.repository.ConversationMessageRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 05.04.2017
 * Group: 11-501
 * Project: vITISe
 */
@Transactional
public class ConversationMessageRepositoryImpl implements ConversationMessageRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ConversationMessage> findAllByConversationAndDatesList(Conversation conversation, List<Date> dates) {
        return this.findAllByConversationAndDates(conversation, dates, null);
    }

    @Override
    public int newMessagesCount(Conversation conversation, List<Date> dates, Date logDate) {
        return this.findAllByConversationAndDates(conversation, dates, logDate).size();
    }


    private List<ConversationMessage> findAllByConversationAndDates(Conversation conversation, List<Date> dates, Date logDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ConversationMessage> query = criteriaBuilder.createQuery(ConversationMessage.class);
        Root<ConversationMessage> root = query.from(ConversationMessage.class);
        Predicate p = criteriaBuilder.equal(root.get(ConversationMessage_.conversation), conversation);

        if (dates.size() > 0) {
            Predicate p2 = criteriaBuilder.lessThan(root.get(ConversationMessage_.date), dates.get(0));
            for (int i = 1; i < dates.size() - 1; i += 2) {
                Predicate p1 = criteriaBuilder.between(root.get(ConversationMessage_.date), dates.get(i), dates.get(i + 1));
                p2 = criteriaBuilder.or(p2, p1);
            }
            if (dates.size() % 2 == 0) {
                Predicate p1 = criteriaBuilder.greaterThan(root.get(ConversationMessage_.date), dates.get(dates.size() - 1));
                p2 = criteriaBuilder.or(p2, p1);
            }
            p = criteriaBuilder.and(p, p2);
        }

        if (logDate != null) {
            Predicate p1 = criteriaBuilder.greaterThan(root.get(ConversationMessage_.date), logDate);
            p = criteriaBuilder.and(p, p1);
        }

        query.where(p);
        query.orderBy(criteriaBuilder.desc(root.get(ConversationMessage_.date)));
        return entityManager.createQuery(query).getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}