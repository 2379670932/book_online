package top.soft.bookonline.service.Impl;

import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.dao.impl.UserDaoImpl;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public User signIn(String account, String password) {
        User user = User.builder().account(account).password(password).build();
        return userDao.findUser(user);
    }

    @Override
    public int register(String account, String password) {
        User user = User.builder().account(account).password(password).build();
        return userDao.insertUser(user);
    }

    @Override
    public int updataImage(String account, String avatar) {
        return userDao.updataImage(account,avatar);
    }

    @Override
    public User userInfo(String account) {
        return userDao.userInfo(account);
    }
}
