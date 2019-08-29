package com.bite.domain;


import lombok.Data;

import java.util.List;

/**
 * 自定义分页组件实体
 * @param <T>
 */
@Data
public class PageBean<T> {

    private Integer totalCount ; //总记录数
    private Integer totalPage;   //总页数
    private Integer currentPage; //当前页码
    private Integer pageSize ;   //每页显示条数
    private List<T> list ;       //当前页面中的集合数据

    public Integer getTotalPage() {
        return  (int)Math.ceil((totalCount*1.0/pageSize)) ;
    }

    public PageBean() {}

    public PageBean(Integer totalCount, Integer currentPage, Integer pageSize, List<T> list) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
    }
}
