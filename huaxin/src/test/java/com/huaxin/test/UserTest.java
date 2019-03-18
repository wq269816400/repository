package com.huaxin.test;

import com.huaxin.dao.IUserDao;
import com.huaxin.pojo.User;
import com.huaxin.service.IUserService;
import com.huaxin.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by wanq on 2019/3/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserTest {
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserDao userDao;

    //查询所有
    @Test
    public void findAll() {
        List<User> list = userService.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void findPage(){
        Page<User> page = userService.findPage(1, 2);
        System.out.println("总页数:"+page.getTotalPages());
        System.out.println("总记录数:"+page.getTotalElements());
        System.out.println("本页记录:"+page.getContent());
    }

    //根据ID查询
    @Test
    public void findById() {
        User user = userService.findById(1);
        System.out.println(user);
    }

    //新增用户
    @Test
    public void save() {
        User user = new User();
        user.setUsername("赵六");
        user.setPassword("45678");
        userService.add(user);
    }

    // 修改用户
    @Test
    public void update() {
        User user = userService.findById(5);
        user.setUsername("田七");
        user.setPassword("1234");
        userService.add(user);
    }
//根据用户名 查询
    @Test
    public void findByUsername() {
        List<User> list = userDao.findByUsernameContains("%赵%");
        for (User user : list) {
            System.out.println(user);
        }
    }
    //根据用户名和密码查询
    @Test
    public  void findUsernameAndPassword(){
        List<User> list = userService.findByUsernameAndPassword("%赵%", "%123%");
        for (User user : list) {
            System.out.println(user);
        }
    }
    //按照排序
    @Test
    public void findAllSort(){
        List<User> list = userService.findAllSort("DESC");
        for (User user : list) {
            System.out.println(user);
        }
    }
    //多条件查询
    @Test
    public void findByExample() {
        List<User> list = userService.findByExample("赵", "5");
        for (User user : list) {
            System.out.println(user);
        }
    }
}
