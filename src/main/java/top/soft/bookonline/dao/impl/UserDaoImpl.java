package top.soft.bookonline.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.util.JdbcUtil;
import top.soft.bookonline.util.Md5Util;

public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.getDataSource());

    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user(account,password,nickname,avatar) values(?,?,?,?)";
        return jdbcTemplate.update(sql,user.getAccount(), Md5Util.crypt(user.getPassword()),user.getNickname(),user.getAvatar());
    }

    @Override
    public User findUser(User user) {
        try {
            String sql = "select * from t_user where account=? and password=?";
            return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),user.getAccount(),Md5Util.crypt(user.getPassword()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updataImage(String account, String avater) {
        try {
            String sql = "UPDATE t_user SET avatar = ? WHERE account = ?";
            return jdbcTemplate.update(sql,avater,account);
        }catch (DataAccessException e){
            e.printStackTrace();
//            System.out.println("报错了");
            return 0;
        }
    }

    @Override
    public User userInfo(String account) {
        try {
            String sql = "SELECT * FROM t_user WHERE account = ?";
            return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),account);
        }catch (DataAccessException e){
            e.printStackTrace();
//            System.out.println("报错了");
            return new User();
        }
    }
}
