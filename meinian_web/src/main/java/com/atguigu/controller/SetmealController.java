package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.SetmealService;
import com.atguigu.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author lbstart
 * @create 2021-06-02 13:05
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
    }

    @RequestMapping("findAll")
    public Result findAll(){
        try {
            List<TravelGroup> list =setmealService.findAll();
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("add")
    public Result add(Integer[] travelgroupIds, @RequestBody Setmeal setmeal) {
        try {
            setmealService.add(travelgroupIds, setmeal);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("upload")
    public Result upload(MultipartFile imgFile){

        try {
            String originalFilename = imgFile.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".");
            String substring = originalFilename.substring(i);
            String s = UUID.randomUUID().toString() + substring;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),s);
            //将上传图片名称存入Redis，基于Redis的Set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,s);
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,s);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping("findById")
    public Result findById(Integer id){
        Setmeal setmeal = null;
        try {
            setmeal = setmealService.findById(id);
            return new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }
    @RequestMapping("edit")
    public Result edit(Integer[] travelgroupIds,@RequestBody Setmeal setmeal){
        setmealService.edit(travelgroupIds,setmeal);
        return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    @RequestMapping("deleteById")
    public Result deleteById(Integer id){
        try {
            setmealService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (RuntimeException e) {
            return new Result(false,e.getMessage());
        } catch (Exception e){
            return new Result(false,MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }
}
