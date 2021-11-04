package com.atguigu.service;

import com.atguigu.pojo.Member;

import java.util.List;

/**
 * @author lbstart
 * @create 2021-06-07 19:39
 */
public interface MemberService {
    Member findByTelephone(String telephone);

    void add(Member member);

    List<Integer> findMemberCountByMonth(List<String> list);
}
