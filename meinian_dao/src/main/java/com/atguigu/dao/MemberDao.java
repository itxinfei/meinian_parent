package com.atguigu.dao;

import com.atguigu.pojo.Member;

/**
 * @author lbstart
 * @create 2021-06-06 19:42
 */
public interface MemberDao {
    Member findByTelephone(String telephone);

    void add(Member member);

    Integer findMemberCountBeforeDate(String lastDayOfMonth);

    int getTodayNewMember(String today);

    int getTotalMember();

    int getThisWeekAndMonthNewMember(String weekMonday);
}
