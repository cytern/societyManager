package com.service;

import com.dao.ActivityDao;
import com.dao.SHonorDao;
import com.dao.SocietyDao;
import com.pojo.Activity;
import com.pojo.SHonor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pojo.BackSociety;
import com.pojo.Society;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现类，社团服务实现类
 */
@Service
public class SocietyServiceImpl implements SocietyService{
@Autowired
    private ActivityDao activityDao;
@Autowired
    private SHonorDao sHonorDao;
@Autowired
    private SocietyDao societyDao;


    public void setSocietyDao(SocietyDao societyDao) {
        this.societyDao = societyDao;
    }

    public void setSHonorDao(SHonorDao sHonorDao) {
        this.sHonorDao = sHonorDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }
  /**
   * @service  主页面服务，获取社团列表。多表读取活动，荣誉
   * @return  BackSociety  特殊封装的实体类，包含社团，荣誉，活动
   **/
  public List<BackSociety> getSocieties() {
      List<BackSociety> backSocieties = new ArrayList<>();
      List<Society> societies = societyDao.getAllSociety();
      for (Society society:societies){
          BackSociety backSociety = new BackSociety();
          backSociety.setConf(society.getConf());
          backSociety.setPurpose(society.getPurpose());
          backSociety.setCTime(society.getCTime());
          List<SHonor> sHonors = sHonorDao.getSHonorBySocietyId(society.getSocietyId());
          List<Activity> activities = activityDao.getActivityById(society.getSocietyId());
          backSociety.setSHonors(sHonors);
          backSociety.setSocietyId(society.getSocietyId());
          backSociety.setActivities(activities);
          backSociety.setSocietyName(society.getSocietyName());
          backSocieties.add(backSociety);
      }
      return backSocieties;
    }

    /**
     * @param society 修改社团服务
     * @return  成功或失败/mybatis特性，返回值为int，描述为修改了几个，如果修改失败，会返回0或其他负数
     */
    public String updateSocieties(Society society){
      int status = societyDao.updateByPrimaryKeySelective(society);
      if (status >0){
          return "修改成功";
      }else {
          return "修改失败";
      }
    }

    /**
     * @param id  id为society_id，删除社团服务，会联合删除所有相关的活动，荣誉，社团
     * @return mybatis特性。
     */
    public String deleteSocieties(Integer id){
        int status = societyDao.deleteByPrimaryKey(id);
               sHonorDao.deById(id);
               activityDao.delById(id);
        if (status >0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     *
     * @param society  添加社团服务
     * @return  mybatis特性。
     */

    public String addSocieties(Society society){
        int status = societyDao.insertSelective(society);
        if (status >0){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    @Override
    public List<Society> getAllS() {
        List<Society> societies = societyDao.getAllSociety();
        return societies;
    }


}
