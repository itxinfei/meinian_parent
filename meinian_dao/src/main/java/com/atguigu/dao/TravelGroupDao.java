package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-01 21:29
 */
public interface TravelGroupDao {
    void add(TravelGroup travelGroup);

    void setTravelGroupAndTravelItem(Map<String, Integer> map);

    Page<TravelGroup> findPage(String queryString);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelGroupId(Integer id);

    List<TravelGroup> findTravelGroupListById(Integer id);

    void edit(TravelGroup travelGroup);

    void deleteTravelGroupAndTravelItemByTravelGroupId(Integer id);

    void deleteById(Integer id);

    Integer idRelatedWithTravelItem(Integer id);

    List<TravelGroup> findAll();

    List<Integer> findTravelGroupIdBySetmealId(Integer id);

    Integer isRelatedWithSetmeal(Integer id);
}
