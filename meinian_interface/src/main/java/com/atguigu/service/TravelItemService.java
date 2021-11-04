package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;

import java.util.List;

/**
 * @author lbstart
 * @create 2021-05-31 18:59
 */

public interface TravelItemService {

    void add(TravelItem travelItem);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    TravelItem getTravelItemById(Integer id);

    void edit(TravelItem travelItem);

    void deleteById(Integer id);

    List<TravelItem> findAll();
}
