package top.soft.bookonline.dao;

import org.junit.jupiter.api.Test;
import top.soft.bookonline.dao.impl.UserDaoImpl;
import top.soft.bookonline.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void insertUser() {
        UserDao userDao = new UserDaoImpl();
        User user = User.builder().nickname("User1").password("123456").
                address("NanJing Jiangsu").account("Account2").avatar("https://img.zcool.cn/community/0101195543065f0000019ae94cc470.jpg@3000w_1l_0o_100sh.jpg").build();
        userDao.insertUser(user);
    }

    @Test
    void findUser() {
        UserDao userDao = new UserDaoImpl();
        userDao.findUser(new User().builder().account("Account1").password("123456").build());
    }
}