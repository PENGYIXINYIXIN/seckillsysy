package com.qfedu.seckillsysy.service;

import com.qfedu.seckillsysy.pojo.User;

/**
 * Created by PENGYIXIN on 2019/7/4.
 */
public interface UserService {
    User findUser(String mobile, String password);
}