package com.huaxin.service.impl;

import com.huaxin.dao.IUserDao;
import com.huaxin.pojo.Result;
import com.huaxin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.huaxin.service.IUserService;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by wanq on 2019/3/15.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    //查询所有
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    //分页查询
    @Override
    public Page<User> findPage(int PageNum, int pageSize) {
        PageRequest pageRequest = new PageRequest(PageNum, pageSize);
        return userDao.findAll(pageRequest);
    }

    //根据ID查询
    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    //新增用户
    @Override
    public Result add(User user) {
        try {
            userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "新增失败");
        }
        return new Result(true, "新增成功");
    }

    //更新用户
    @Override
    public Result update(User user) {
        User user1 = userDao.findById(user.getId());
        if (user == null) {
            return new Result(false, "用户不存在");
        }
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        try {
            userDao.save(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新失败");
        }
        return new Result(true, "更新成功");
    }

    //删除用户
    public Result delete(int id) {
        try {
            userDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
        return new Result(true, "删除成功");
    }

    //根据用户名查询
    @Override
    public List<User> findByUsername(String username) {
        username = "%" + username + "%";
        return userDao.findByUsernameContains(username);

    }

    //根据用户名和密码查询
    @Override
    public List<User> findByUsernameAndPassword(String username, String password) {
        username = "%" + username + "%";
        password = "%" + password + "%";
        return userDao.findByUsernameAndPassword(username, password);
    }


    //按照id排序
    @Override
    public List<User> findAllSort(String order) {
        if ("DESC".equalsIgnoreCase(order)) {
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return userDao.findAll(sort);
        } else {
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            return userDao.findAll(sort);
        }
    }

    //根据用户名或者密码查询
    @Override
    public List<User> findByExample(String username, String password) {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> name = root.get("username");
                Predicate p1 = cb.like(name.as(String.class), "%" + username + "%");
                Path<Object> word = root.get("password");
                Predicate p2 = cb.like(word.as(String.class), "%" + password + "%");
                return cb.or(p1, p2);
            }
        };
        return userDao.findAll(spec);
    }

}
