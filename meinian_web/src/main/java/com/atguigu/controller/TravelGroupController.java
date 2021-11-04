package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lbstart
 * @create 2021-06-01 21:25
 */
@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {

    @Reference
    private TravelGroupService travelGroupService;

    @RequestMapping("add")
    public Result add(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup){
        try {
            travelGroupService.add(travelItemIds,travelGroup);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }
    @RequestMapping("findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
       return travelGroupService.findPage(queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
    }

    @RequestMapping("findById")
    public Result findById(Integer id){
        TravelGroup travelGroup = travelGroupService.findById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
    }

    @RequestMapping("findTravelItemIdByTravelGroupId")
    public List<Integer> findTravelItemIdByTravelGroupId(Integer id){
      return travelGroupService.findTravelItemIdByTravelGroupId(id);
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] travelItemIds,@RequestBody TravelGroup travelGroup ){
        travelGroupService.edit(travelItemIds,travelGroup);
        return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
    }

    @RequestMapping("delete")
    public Result delete(Integer id){
        try {
            travelGroupService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        }catch (RuntimeException e){
            // 运行时异常，表示自由行和跟团游的关联表中存在数据
            return new Result(false,e.getMessage());
        }catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("findAll")
    public List<TravelGroup>  findAll(){
      return  travelGroupService.findAll();
    }

    @RequestMapping("findTravelGroupIdBySetmealId")
    public List<Integer> findTravelGroupIdBySetmealId(Integer id){
        return travelGroupService.findTravelGroupIdBySetmealId(id);
    }

}
