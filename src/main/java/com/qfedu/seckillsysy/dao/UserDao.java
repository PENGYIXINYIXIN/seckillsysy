package com.qfedu.seckillsysy.dao;

import com.qfedu.seckillsysy.pojo.User;

/**
 * Created by PENGYIXIN on 2019/7/4.
 */
public interface UserDao {
    User findUserByMobile(String mobile);
}