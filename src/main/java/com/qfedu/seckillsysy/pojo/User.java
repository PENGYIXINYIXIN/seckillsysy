package com.qfedu.seckillsysy.pojo;

/**
 * Created by PENGYIXIN on 2019/7/4.
 */

import lombok.Data;

@Data
public class User {
    private Integer uid;
    private String mobile;
    private String password;
    private String location;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



}