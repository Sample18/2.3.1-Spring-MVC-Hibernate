package web.dao;


import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    void remove(int id);

    void create(User user);

    void update(User user);

    User findById(int id);

}
