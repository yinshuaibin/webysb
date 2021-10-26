package com.ysb.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author yinshuaibin
 * @date 2021/10/25 22:06
 * @description
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "cosmetics_type")
public class CosmeticsType {

    @Id
    @Column(columnDefinition="int COMMENT '主键，自动生成'")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition="varchar(50) not null COMMENT'类别id'")
    private String typeId;

    @Column(columnDefinition="varchar(50) not null COMMENT'名称'")
    private String name;

}
