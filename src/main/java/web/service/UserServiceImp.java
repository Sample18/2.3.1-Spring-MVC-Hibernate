package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public void remove(int id) {
        userDAO.remove(id);
    }

    @Transactional
    @Override
    public void create(User user) {
        userDAO.create(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }
}
