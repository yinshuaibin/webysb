package com.ysb.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 化妆品进货表
 * @author yinshuaibin
 * @date 2021/10/24 14:54
 * @description
 */
@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cosmetics_stock")
public class CosmeticsStock {

    @Id
    @Column(columnDefinition="int COMMENT '主键，自动生成'")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition="varchar(50) COMMENT'类别id'")
    private String stockType;

    @Column(columnDefinition="varchar(32) COMMENT'进货客户'")
    private String stockClient;

    @Column(columnDefinition="int not null COMMENT'进货数量'")
    private int stockNumber;

    @Column(columnDefinition="datetime COMMENT'进货时间'")
    private Date stockTime;

    @Column(columnDefinition="varchar(256) COMMENT'备注'")
    private String remark;

    @CreatedDate
    @Column(columnDefinition="datetime not null COMMENT'创建时间'")
    private Date createTime;

    @LastModifiedDate
    @Column(columnDefinition="datetime COMMENT'更新时间'")
    private Date updateTime;
}
