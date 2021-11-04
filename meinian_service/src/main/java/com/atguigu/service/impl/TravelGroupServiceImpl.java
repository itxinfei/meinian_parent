package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupDao;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-01 21:27
 */
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {

    @Autowired
    private TravelGroupDao travelGroupDao;

    @Override
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.add(travelGroup);
        setTravelGroupAndTravelItem(travelGroup.getId(),travelItemIds);
    }

    private void setTravelGroupAndTravelItem(Integer travelGroupId, Integer[] travelItemIds) {
        if (travelItemIds!=null && travelItemIds.length>0){
            for (Integer travelItemId : travelItemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("travelGroup",travelGroupId);
                map.put("travelItem",travelItemId);
                travelGroupDao.setTravelGroupAndTravelItem(map);
            }
        }
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<TravelGroup> page=travelGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public TravelGroup findById(Integer id) {
        return travelGroupDao.findById(id);
    }

    @Override
    public List<Integer> findTravelItemIdByTravelGroupId(Integer id) {
        return travelGroupDao.findTravelItemIdByTravelGroupId(id);
    }

    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        // 1：修改跟团游的基本信息
        travelGroupDao.edit(travelGroup);
        /**
         * 2：修改跟团游和自由行的中间表（先删除，再创建）
         * 之前的数据删除
         * 再新增页面选中的数据
         */
        // 删除之前中间表的数据
        travelGroupDao.deleteTravelGroupAndTravelItemByTravelGroupId(travelGroup.getId());
        // 再新增页面选中的数据
        setTravelGroupAndTravelItem(travelGroup.getId(),travelItemIds);

    }

    @Override
    public void deleteById(Integer id) {
        //在删除前，要先查询数据是否存在关联数据，如果存在关联数据，就不删除
        Integer count1 = travelGroupDao.idRelatedWithTravelItem(id);  //count1指 跟团游-自由行关联表 中与这个id对应的数据的条目数
        Integer count2 = travelGroupDao.isRelatedWithSetmeal(id);  //count1指 跟团游-套餐游关联表 中与这个id对应的数据的条目数
        if (count1>0 && count2==0){
            throw new RuntimeException("存在与自由行相关的数据，无法删除");
        }
        if(count2>0 && count1==0){
            throw new RuntimeException("存在与套餐游关联数据，无法删除");
        }
        if (count1>0 && count2>0){
            throw new RuntimeException("与自由行和套餐游数据均关联，无法删除");
        }
        travelGroupDao.deleteById(id);
    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupDao.findAll();
    }

    @Override
    public List<Integer> findTravelGroupIdBySetmealId(Integer id) {
      return  travelGroupDao.findTravelGroupIdBySetmealId(id);
    }


}
