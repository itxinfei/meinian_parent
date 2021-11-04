package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.RedisConstant;
import com.atguigu.dao.SetmealDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-02 13:07
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Setmeal findById(Integer id) {
       return setmealDao.findById(id);
    }

    @Override
    public void edit(Integer[] travelgroupIds, Setmeal setmeal) {
        //更新新的套餐信息
        setmealDao.edit(setmeal);
        //将之前的中间表中的数据删除
        setmealDao.deleteSetmealAndTravelGroup(setmeal.getId());
        //添加新的中间表数据
        setSetmealAndTravelGroup(setmeal.getId(),travelgroupIds);
    }

    @Override
    public void deleteById(Integer id) {
      Integer count =   setmealDao.isRelatedData(id);
      if(count>0){
          throw new RuntimeException("存在关联数据，无法删除");
      }else {
          setmealDao.deleteById(id);
      }
    }

    @Override
    public List<Setmeal> getSetmeal() {
     return  setmealDao.getSetmeal();
    }

    @Override
    public Setmeal findBySetmealId(int id) {
        return setmealDao.findBySetmealId(id);
    }

    @Override
    public Setmeal getById(int id) {
        return setmealDao.getById(id);
    }

    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return setmealDao.findSetmealCount();
    }

    @Override
    public List<TravelGroup> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public void add(Integer[] travelgroupIds, Setmeal setmeal) {
        setmealDao.add(setmeal);
        Integer setmealId = setmeal.getId();
        if(travelgroupIds!=null && travelgroupIds.length >0) {
            setSetmealAndTravelGroup(setmealId, travelgroupIds);
        }
        //将图片名称保存到Redis
        savePic2Redis(setmeal.getImg());

    }

    //将图片名称保存到Redis
    private void savePic2Redis(String img) {
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,img);
    }

    private void setSetmealAndTravelGroup(Integer setmealId, Integer[] travelgroupIds) {
            for (Integer travelgroupId : travelgroupIds) {
                setmealDao.setSetmealAndTravelGroup(setmealId,travelgroupId);
            }
    }

}
