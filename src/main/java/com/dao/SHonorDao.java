package com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.pojo.SHonor;

import java.util.List;
@Repository
@Mapper
public interface SHonorDao {
    int deleteByPrimaryKey(Integer sHonorId);

    int insert(SHonor record);

    int insertSelective(SHonor record);

    SHonor selectByPrimaryKey(Integer sHonorId);

    int updateByPrimaryKeySelective(SHonor record);

    int updateByPrimaryKey(SHonor record);

    List<SHonor> getSHonorBySocietyId(Integer societyId);

    int deById(Integer id);
}