package com.qfedu.seckillsysy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // 包含@Setter和@Getter
@ToString
@NoArgsConstructor   // 生成无参的构造方法
@AllArgsConstructor  // 生成带所有属性的构造方法
public class Goods {

    private Integer gid;
    private String gname;
    private Double price;
    private Integer stock;
    private String imgPath;
    private String staticUrl;
}
