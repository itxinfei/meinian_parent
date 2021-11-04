package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lbstart
 * @create 2021-05-31 19:00
 */
@Service(interfaceClass =TravelItemService.class)
@Transactional  //后期考虑这里用不用加(readOnly = true)
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    TravelItemDao travelItemDao;

    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<TravelItem> page =travelItemDao.findPage(queryString);
        return new PageResult(page.getTotal(),page);
    }

    @Override
    public TravelItem getTravelItemById(Integer id) {
        return travelItemDao.getTravelItemById(id);

    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    @Override
    public void deleteById(Integer id) {
        //在删除前，要先查询数据是否存在关联数据，如果存在关联数据，就不删除
        Integer count = travelItemDao.countTravelItemIds(id);  //count指关联表中与这个id对应的数据的条目数
        if(count>0){
            throw new RuntimeException("存在关联数据，无法删除");
        }
        travelItemDao.deleteById(id);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemDao.findAll();
    }
}
