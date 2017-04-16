package ru.kpfu.itis.group11501.vitise.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.group11501.vitise.model.*;
import ru.kpfu.itis.group11501.vitise.repository.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Bogdan Popov on 07.04.2017.
 */
@Transactional
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAllByIsActiveAndStatusId(Boolean isActive, List<Long> idStatusList, String[] args) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Что ищем
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        // Где ищем
        Root<UsersStatus> root = query.from(UsersStatus.class);
        Join<UsersStatus, User> join = root.join(UsersStatus_.user);
        Join<UsersStatus, Status> join2 = root.join(UsersStatus_.status);
        query.select(join);
        query.distinct(true);
        Predicate p;
        if (isActive == null) {
            p = criteriaBuilder.isNotNull(join.get(User_.isActive));
        } else {
            p = criteriaBuilder.equal(join.get(User_.isActive), isActive);
        }
        if (!idStatusList.isEmpty()) {
            Predicate p2 = criteriaBuilder.equal(join2.get(Status_.id), idStatusList.remove(0));
            for (Long id : idStatusList) {
                Predicate p1 = criteriaBuilder.equal(join2.get(Status_.id), id);
                p2 = criteriaBuilder.or(p2, p1);
            }
            p = criteriaBuilder.and(p, p2);
        }
        for (String arg : args) {
            if (!arg.isEmpty()) {
                Predicate p3 = criteriaBuilder.like(join.get(User_.name), arg + "%");
                Predicate p4 = criteriaBuilder.like(join.get(User_.surname), arg + "%");
                Predicate p5 = criteriaBuilder.like(join.get(User_.thirdName), arg + "%");
                p3 = criteriaBuilder.or(p3, p4);
                p5 = criteriaBuilder.or(p4, p5);
                p4 = criteriaBuilder.or(p3, p5);
                p = criteriaBuilder.and(p, p4);
            }
        }
        query.orderBy(criteriaBuilder.asc(join.get(User_.surname)));
        query.where(p);
        return entityManager.createQuery(query).getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
