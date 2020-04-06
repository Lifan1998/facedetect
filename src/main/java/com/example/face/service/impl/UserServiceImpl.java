package com.example.face.service.impl;

import com.example.face.enmu.LoginType;
import com.example.face.entity.User;
import com.example.face.dao.UserDao;
import com.example.face.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-03-30 20:51:27
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    @Override
    public ResponseEntity login(int type, String account, String password) {
        if (type == LoginType.EMAIL.getValue()) {
            User user = userDao.queryByEmail(account);
            if (user == null) {
                user = new User();
                user.setEmail(account);
                user.setPassword(password);
                user.setUsername("用户" + account);
                userDao.insert(user);
                return ResponseEntity.ok("账号不存在，已为您创建");
            }

            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok("登录成功");
            } else {
                return new ResponseEntity<>("密码错误", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("不支持的登录方式", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }
}