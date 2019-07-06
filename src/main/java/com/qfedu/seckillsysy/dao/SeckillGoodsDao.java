package com.qfedu.seckillsysy.dao;

import com.qfedu.seckillsysy.pojo.SeckillGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeckillGoodsDao {
    public List<SeckillGoods> selectAll();

    public SeckillGoods selectById(Integer sid);

    //查秒杀商品的秒杀库存
    public Integer selectSeckillStock(Integer sid);
    //减库存
    public  void updaSeckillStock(Integer sid);

    public  void updateStaticUrl(@Param("staticUrl") String staticUrl,@Param("sid") Integer sid);
}
