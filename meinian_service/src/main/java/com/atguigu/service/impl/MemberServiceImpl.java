package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.pojo.Member;
import com.atguigu.service.MemberService;
import com.atguigu.util.DateUtils;
import com.atguigu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lbstart
 * @create 2021-06-07 19:39
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        Member byTelephone = memberDao.findByTelephone(telephone);
        return byTelephone;
    }

    @Override
    public void add(Member member) {
        if (member.getPassword() != null) {
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCountByMonth(List<String> monthsList) {
        // 根据月份统计会员数量
        List<Integer> memeberCountList = new ArrayList<Integer>();
        if(monthsList!=null && monthsList.size()>0){
            for (String months : monthsList) {
                //String regTime = months+"-31";
                // 获取指定月份的最后一天
                String lastDayOfMonth =  DateUtils.getLastDayOfMonth(months);
                //  迭代过去12个月，每个月注册会员的数量，根据注册日期查询
                Integer memeberCount = memberDao.findMemberCountBeforeDate(lastDayOfMonth);
                memeberCountList.add(memeberCount);
            }
        }
        return memeberCountList;
    }
}
