package com.ysb.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yinshuaibin
 * @date 2021/10/25 22:06
 * @description
 */
@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(columnDefinition="datetime not null COMMENT'创建时间'")
    private Date createTime;
}
