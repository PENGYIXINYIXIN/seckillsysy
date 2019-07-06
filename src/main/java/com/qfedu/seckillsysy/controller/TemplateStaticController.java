package com.qfedu.seckillsysy.controller;

import com.qfedu.seckillsysy.pojo.SeckillGoods;
import com.qfedu.seckillsysy.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class TemplateStaticController {

    @Autowired
    @Qualifier("myTemplateEngine")
    private TemplateEngine templateEngine;


    @Autowired
    private SeckillGoodsService seckillGoodsService;

    //根据商品id生成静态页面
    @RequestMapping("/create")
    @ResponseBody
    public String createStaticHtml(Integer sid){
        SeckillGoods seckillGoods = seckillGoodsService.selectById(sid);

        //模板的上下文对象  存放模板中需要的上下文的数据
        Context context = new Context();
        context.setVariable("goods",seckillGoods);

        //生成静态文件的位置
        //获取编译classes文件夹的绝对路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
         try {
             //处理中文字符乱码  不能识别路径的问题
            path = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //生成的文件名，用来存放在数据库中前台拿到静态化页面之后的路径
        String fileName ="seckill_detail" + sid +".html";
        //生成的文件的存放的路径  此处存放的位置是编译之后的存放的位置
        String newFile = path +"static/" + fileName ;
        //判断要创建的文件是否存在  如果不存在就创建文件  写入文件内容
        File file = new File(newFile);
        FileWriter writer = null;
        if(!file.exists()){
            try {
                file.createNewFile();
                } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
             writer = new FileWriter(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成静态文件
        templateEngine.process("seckill_detail",context,writer);

        seckillGoodsService.updataStaticUrl(fileName,seckillGoods.getSid());
        return "success";
    }
}
