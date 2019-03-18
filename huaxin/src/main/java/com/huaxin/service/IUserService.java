package com.huaxin.service;

import com.huaxin.pojo.Result;
import com.huaxin.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wanq on 2019/3/15.
 */
public interface IUserService {
    List<User> findAll();

    Page<User> findPage(int pageNum,int pageSize);

    User findById(int id);

    Result add(User uesr);
    Result update(User uesr);
    Result delete(int id);

    List<User> findByUsername(String username);

    List<User> findAllSort(String order);

    List<User> findByUsernameAndPassword(String username, String password);

    List<User> findByExample(String username, String password);
}
