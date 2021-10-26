package com.ysb.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageDTO<T> {

    private List<T> list;

    private Long total;

    public PageDTO(Page<T> page) {
        this.list = page.getContent();
        this.total = page.getTotalElements();
    }
}
