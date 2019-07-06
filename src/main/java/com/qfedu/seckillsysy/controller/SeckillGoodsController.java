package com.qfedu.seckillsysy.controller;

import com.qfedu.seckillsysy.common.RedisLock;
import com.qfedu.seckillsysy.pojo.SeckillGoods;
import com.qfedu.seckillsysy.pojo.User;
import com.qfedu.seckillsysy.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class SeckillGoodsController {
    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private RedisLock redisLock;
    @RequestMapping("/redistest")
    @ResponseBody
    public  String testRedis(){
       redisLock.test();
        return "success";
    }
    @RequestMapping("list.do")
    public  String select(Model model){
        List<SeckillGoods> seckillGoods = seckillGoodsService.selectAll();
        System.out.println(seckillGoods);
        model.addAttribute("seckillGoods",seckillGoods);
      /*  System.out.println("Ctrl + Shift + F9   " + "Ctrl + F9");  手动编译 */
        return "goods_list";
    }
    @RequestMapping("detail")
    public String selectById(Integer sid,Model model){
        SeckillGoods seckillGoods = seckillGoodsService.selectById(sid);
        model.addAttribute("goods",seckillGoods);

        //还可以得到剩余时间
        Date currentDate = new Date();//当前时间
        Date beginTime = seckillGoods.getBeginTime();//开始时间
        Date endTime = seckillGoods.getEndTime();//结束时间
        long time1 = beginTime.getTime() -currentDate.getTime();
        int seckillStatus =0 ;//秒杀状态
        long remainSecond = 0;//剩余时间
        if (time1>0){//说明还没有开始
            seckillStatus = 0;
            remainSecond = time1/1000;//开始时间倒计时
        }else {
            long time2  = endTime.getTime() - currentDate.getTime();
            if(time2>0){
                if(seckillGoods.getSeckillStock()==0){
                    seckillStatus=2;//库存为0 秒杀结束
                }else{
                    seckillStatus = 1;//秒杀中
                }
            }else{
                seckillStatus =2 ;//秒杀结束
            }
        }
        model.addAttribute("seckillStatus" ,seckillStatus);
        model.addAttribute("remainSeconds",remainSecond);
        return "seckill_detail";
    }

    //秒杀商品的操作
    @RequestMapping("/seckill")
    public String seckillGoods(Integer sid,Model model ,HttpSession session ){
        //秒杀商品的时候 先判断用户是否登录  如果没有登录就跳转到用户登录页面
       //登录的时候将用户存在session里面  可以从session中获取用户的信息
        User user = (User)session.getAttribute("user");
        if(user ==null ){
            return "login";
        }
        //分布式锁  解决多线程高并发的现象
        //加锁  String.valueOf(int i) : 将 int 变量 i 转换成字符串
        //（8）String.valueOf(long l) : 将 long 变量 l 转换成字符串
        long v =System.currentTimeMillis()+ (long)(Math.random()* 1000);
        boolean lock = redisLock.lock(String.valueOf(sid), String.valueOf(v));
        if(lock){
            try {
                //秒杀进行时  多线程状态下 先判断商品库存
                seckillGoodsService.killStock(sid,user);
            }catch (Exception e){
                model.addAttribute("errmsg",e.getMessage());
                return "miaosha_fail";
            }finally {
                //解锁操作
                redisLock.unlock(String.valueOf(sid), String.valueOf(v));
            }
        }else{
            return "miaosha_fail";
        }
        //获取到商品的库存   seckillStock ：商品库存
        //生成订单
        //减库存



        return "redirect:/orders?uid="+user.getUid();
        //秒杀成功返回订单页面
        //return "goods_list";
    }
    //生成静态页面的时候使用此方法进行初始数据的绑定  优化查询秒杀的速
    @RequestMapping("/seckillInfo")
    @ResponseBody
    public Map<String,Object> seckillInfo(Integer sid){
        SeckillGoods seckillGoods = seckillGoodsService.selectById(sid);

        //还可以得到剩余时间
        Date currentDate = new Date();//当前时间
        Date beginTime = seckillGoods.getBeginTime();//开始时间
        Date endTime = seckillGoods.getEndTime();//结束时间
        long time1 = beginTime.getTime() -currentDate.getTime();
        int seckillStatus =0 ;//秒杀状态
        long remainSecond = 0;//剩余时间
        //表示执行秒杀时候的路径
        String seckillUrl="#";
        if (time1>0){//说明还没有开始
            seckillStatus = 0;
            remainSecond = time1/1000;//开始时间倒计时
        }else {
            long time2  = endTime.getTime() - currentDate.getTime();
            if(time2>0){
                if(seckillGoods.getSeckillStock()==0){
                    seckillStatus=2;//库存为0 秒杀结束
                    remainSecond =-1;
                }else{
                    seckillStatus = 1;//秒杀中
                    seckillUrl = "seckill";//秒杀状态时，给出路径
                    remainSecond = 0;//剩余时间
                }
            }else{
                seckillStatus =2 ;//秒杀结束
                remainSecond=time2;
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("seckillStatus",seckillStatus);
        map.put("remainSeconds",remainSecond);
        map.put("seckillUrl",seckillUrl);
        map.put("seckillStock",seckillGoods.getSeckillStock());
        return map;
    }

    /**
     * 当商品的秒杀的状态是倒计时时 不显示秒杀的路径 防止查看源码的时候直接看到后天的请求的路径 直接请求路径
     *  直接请求后台的秒杀的路径
     * 页面刷新为可秒杀时候请求查询 秒杀的路径
     * @return
     */
    @RequestMapping("/query.do")
    @ResponseBody
    public String getStaticUrl(){
        return "seckill";
    }
}
