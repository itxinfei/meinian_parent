package com.atguigu.dao;

import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author lbstart
 * @create 2021-05-31 19:00
 */

public interface TravelItemDao {

    void add(TravelItem travelItem);

    Page<TravelItem> findPage(String queryString);

    TravelItem getTravelItemById(Integer id);

    void edit(TravelItem travelItem);

    void deleteById(Integer id);

    List<TravelItem> findAll();

    Integer countTravelItemIds(Integer id);

    List<TravelItem> findTravelItemListById(Integer id);
}
