package com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.pojo.Society;

import java.util.List;
@Repository
@Mapper
public interface SocietyDao {
    int deleteByPrimaryKey(Integer societyId);

    int insert(Society record);

    int insertSelective(Society record);

    Society selectByPrimaryKey(Integer societyId);

    int updateByPrimaryKeySelective(Society record);

    int updateByPrimaryKey(Society record);

    List<Society> getAllSociety();

    int deleteAll(Integer id);
}