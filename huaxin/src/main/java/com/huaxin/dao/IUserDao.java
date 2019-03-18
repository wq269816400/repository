package com.huaxin.dao;

import com.huaxin.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wanq on 2019/3/15.
 */

public interface IUserDao extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User>{
    User findById(int id);
    //模糊查询
    List<User> findByUsernameContains(String username);
    //多条件查询、
    @Query("from User where username like ? and password like ?")
    List<User> findByUsernameAndPassword(String username,String password);
}
