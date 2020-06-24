package com.dao;

import com.pojo.SNews;

import java.util.List;

public interface SNewsDao {
    int deleteByPrimaryKey(Integer newsId);

    int insert(SNews record);

    int insertSelective(SNews record);

    SNews selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(SNews record);

    int updateByPrimaryKey(SNews record);

    List<SNews> getAllNews();
}