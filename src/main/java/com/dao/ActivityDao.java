package com.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.pojo.Activity;

import java.util.List;

/**
 * dao层中一个实体类对应一个dao，对应一个xml文件，通过mybatis操作数据库，可以方便管理sqlsession
 */
public interface ActivityDao {
    int deleteByPrimaryKey(Integer activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> getActivityById(Integer societyId);

    int delById(Integer id);
}