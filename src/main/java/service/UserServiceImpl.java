package service;

import dao.UserDao;
import dao.UserDaoIml;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private static final UserService userService = new UserServiceImpl();

    private final UserDao UserDao = UserDaoIml.getInstance();

    public static UserService getInstance() {
        return userService;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean register(String username, String password) {
        return false;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void blockUser(long id) {

    }

    @Override
    public void unBlockUser(long id) {

    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
