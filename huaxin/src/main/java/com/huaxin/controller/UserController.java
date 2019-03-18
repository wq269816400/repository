package com.huaxin.controller;

import com.huaxin.pojo.Result;
import com.huaxin.pojo.User;
import com.huaxin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wanq on 2019/3/15.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    //查询所有
    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    //根据ID进行查询
    @RequestMapping("/findById/{id}")
    public User findById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    //添加新用户
    @RequestMapping("/add")
    public Result addUser(@RequestBody User user) {
        return userService.add(user);
    }
    //更新用户
    @RequestMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.update(user);
    }

    //根据删除用户
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable("id") int id) {
        return userService.delete(id);
    }

    //分页查询
    @RequestMapping("/findPage/{pageNum}/{pageSize}")
    public Page<User> findPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return userService.findPage(pageNum, pageSize);
    }

}
