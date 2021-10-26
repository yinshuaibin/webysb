package com.ysb.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author yinshuaibin
 * @date 2021/10/24 14:54
 * @description
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "cosmetics_stock")
public class CosmeticsStock {

    @Id
    @Column(columnDefinition="int COMMENT '主键，自动生成'")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition="varchar(50) COMMENT'类别id'")
    private String stockType;

    @Column(columnDefinition="int not null COMMENT'数量'")
    private int stockNumber;

    @Column(columnDefinition="datetime COMMENT'时间'")
    private String stockTime;

    @Column(columnDefinition="datetime not null COMMENT'创建时间'")
    private String createTime;

    @Column(columnDefinition="datetime COMMENT'更新时间'")
    private String updateTime;
}
