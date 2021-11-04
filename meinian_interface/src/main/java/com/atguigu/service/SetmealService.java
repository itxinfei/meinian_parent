package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;
import com.atguigu.pojo.TravelGroup;

import java.util.List;
import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-02 13:07
 */
public interface SetmealService {


    List<TravelGroup> findAll();

    void add(Integer[] travelgroupIds, Setmeal setmeal);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    Setmeal findById(Integer id);

    void edit(Integer[] travelgroupIds, Setmeal setmeal);

    void deleteById(Integer id);

    List<Setmeal> getSetmeal();

    Setmeal findBySetmealId(int id);

    Setmeal getById(int id);

    List<Map<String, Object>> findSetmealCount();

}
