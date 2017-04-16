package ru.kpfu.itis.group11501.vitise.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Status;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.UsersStatus;
import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;
import ru.kpfu.itis.group11501.vitise.repository.StatusRepository;
import ru.kpfu.itis.group11501.vitise.repository.UserRepository;
import ru.kpfu.itis.group11501.vitise.repository.UsersStatusRepository;
import ru.kpfu.itis.group11501.vitise.service.StatusService;
import ru.kpfu.itis.group11501.vitise.service.StudentService;
import ru.kpfu.itis.group11501.vitise.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UsersStatusRepository usersStatusRepository;
    private final StatusService statusService;
    private final StudentService studentService;
    private final StatusRepository statusRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UsersStatusRepository usersStatusRepository,
                           StatusService statusService,
                           StudentService studentService, StatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.usersStatusRepository = usersStatusRepository;
        this.statusService = statusService;
        this.studentService = studentService;
        this.statusRepository = statusRepository;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getAdmin() {
        Status status = statusRepository.findOneByName("ADMIN");
        List<UsersStatus> usersStatuses = usersStatusRepository.findAllByStatusId(status.getId());
        if (usersStatuses != null) {
            return usersStatuses.get(0).getUser();
        }
        return null;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<Status> getStatus(User user) {
        List<Status> statuses = new ArrayList<>();
        List<UsersStatus> usersStatuses = usersStatusRepository.findAllStatusIdByUserId(user.getId());
        for (UsersStatus usersStatus : usersStatuses) {
            statuses.add(usersStatus.getStatus());
        }
        return statuses;
    }

    @Override
    public List<User> getNewUsers(Status status) {
        System.out.println(status.getName());
        List<User> users = userRepository.findNewByStatus(status);
        System.out.println(users);
        for (User user : users) {
            if (this.isWorker(user))
                user.setStatusName(this.getWorkerPosition(user));
            else
                user.setGroup(studentService.getStudentGroup(user));
        }
        return users;
    }

    @Override
    public List<User> getNewUsers() {
        return userRepository.findAllByIsActive(null);
    }

    @Override
    public void approveUser(Long id) {
        User user = getUser(id);
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public boolean isWorker(User user) {
        List<Status> statuses = getStatus(user);
        for (Status status : statuses) {
            if (status.getName().equals("WORKER")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getConfirmedUsers() {
        return userRepository.findAllByIsActiveNotNullOrderBySurname();
    }

    @Override
    public void changeState(Long id) {
        User user = getUser(id);
        if (user.isActive()) {
            user.setActive(false);
        } else {
            user.setActive(true);
        }
        userRepository.save(user);
    }


    @Override
    public List<User> filterUserListByCriteria(Boolean isActive, List<StatusName> statusNameList, String[] args) {
        List<Long> statusIdList = new ArrayList<>();
        for (StatusName statusName : statusNameList) {
            statusIdList.add(statusService.getStatus(statusName).getId());
        }
        return userRepository.findAllByIsActiveAndStatusId(isActive, statusIdList, args);
    }


    private StatusName getWorkerPosition(User user) {
        List<Status> statuses = getStatus(user);
        for (Status status : statuses) {
            if (!status.getName().equals("WORKER")) {
                return StatusName.valueOf(status.getName());
            }
        }
        return null;
    }
}