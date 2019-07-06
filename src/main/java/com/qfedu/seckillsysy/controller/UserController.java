package com.qfedu.seckillsysy.controller;

import com.qfedu.seckillsysy.common.JsonBean;
import com.qfedu.seckillsysy.pojo.User;
import com.qfedu.seckillsysy.service.UserService;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PENGYIXIN on 2019/7/4.
 */
@Controller

public class UserController {

    @Autowired(required = false)
    private UserService userService;


    @RequestMapping("/denglu")
    public  String a(){
        return "login";
    }


    @RequestMapping("/user/login")
    @ResponseBody
   /* public JsonBean userLogin(String mobile, String password, HttpSession session){
        User user = userService.findUser(mobile, password);
        session.setAttribute("user",user);
        System.out.println(session.getAttribute("user")+"+++++++++++++++++");
        return new JsonBean(1,null);
    }*/
   public Map<String,Object> login(String mobile,String password,HttpSession session ){
       Map<String, Object> map = new HashMap<>();
       try{
           User user = userService.findUser(mobile, password);
           session.setAttribute("user",user);
           map.put("code",0);
       }catch (Exception e){
             map.put("code", 1);
             map.put("msg",e.getMessage());
       }
       return map;
    }

}