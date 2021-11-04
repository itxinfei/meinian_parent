package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-02 13:09
 */
public interface SetmealDao {
    List<TravelGroup> findAll();

    void add(Setmeal setmeal);

    void setSetmealAndTravelGroup(@Param("setmealId") Integer setmealId, @Param("travelgroupId") Integer travelgroupId);

    Page<Setmeal> findPage(String queryString);

    Setmeal findById(Integer id);

    void edit(Setmeal setmeal);

    void deleteSetmealAndTravelGroup(Integer id);

    Integer isRelatedData(Integer id);

    void deleteById(Integer id);

    List<Setmeal> getSetmeal();

    Setmeal findBySetmealId(Integer id);

    Setmeal getById(int id);

    List<Map<String, Object>> findSetmealCount();

}
