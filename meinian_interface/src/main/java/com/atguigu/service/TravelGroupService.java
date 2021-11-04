package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

/**
 * @author lbstart
 * @create 2021-06-01 21:26
 */
public interface TravelGroupService {
    void add(Integer[] travelItemIds, TravelGroup travelGroup);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelGroupId(Integer id);

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    void deleteById(Integer id);

    List<TravelGroup> findAll();

    List<Integer> findTravelGroupIdBySetmealId(Integer id);
}
