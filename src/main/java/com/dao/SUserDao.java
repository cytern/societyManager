package com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.pojo.SUser;

import java.util.List;
@Repository
@Mapper
public interface SUserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(SUser record);

    int insertSelective(SUser record);

    SUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SUser record);

    int updateByPrimaryKey(SUser record);

    List<SUser> getAllUser();


    SUser getByUserName(String userName);
}