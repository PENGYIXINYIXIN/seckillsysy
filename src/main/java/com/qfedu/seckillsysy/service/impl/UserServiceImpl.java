package com.qfedu.seckillsysy.service.impl;

import com.qfedu.seckillsysy.dao.UserDao;
import com.qfedu.seckillsysy.pojo.User;
import com.qfedu.seckillsysy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by PENGYIXIN on 2019/7/4.
 */
@Service

@Transactional
public class UserServiceImpl implements UserService {

@Autowired(required = false)
private UserDao userDao;
    @Override
    public User findUser(String mobile, String password) {
        User user = userDao.findUserByMobile(mobile);
        if(user == null){
            throw new RuntimeException("用户名不存在");
        }
        if(!password.equals(user.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return user;
    }
}