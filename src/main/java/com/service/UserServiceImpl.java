package com.service;

import com.dao.SUserDao;
import org.springframework.stereotype.Service;
import com.pojo.SUser;

import java.util.List;

/**
 * 用户接口实现类
 */
@Service
public class UserServiceImpl implements UserService{
    private SUserDao sUserDao;
    public void setSUserDao(SUserDao sUserDao) {
        this.sUserDao = sUserDao;
    }

    /**
     *    获取用户
     * @return  全部用户列表
     */
    @Override
    public List<SUser> getAllUser() {
        List<SUser> sUsers = sUserDao.getAllUser();
        return sUsers;
    }

    /**
     *   删除用户
     * @param id  用户id
     * @return   mybatis特性
     */
    @Override
    public String deleteUser(Integer id) {
        int status = sUserDao.deleteByPrimaryKey(id);
        if (status >0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     *  修改用户
     * @param sUser
     * @return
     */
    @Override
    public String updateUser(SUser sUser) {
        int status = sUserDao.updateByPrimaryKeySelective(sUser);
        if (status >0){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    /**
     * 添加用户
     * @param sUser
     * @return
     */
    @Override
    public String addUser(SUser sUser) {
        SUser sUser1 = sUserDao.getByUserName(sUser.getUserName());
        try {
            if (sUser1.getUserId() != 0){
                return "重复的用户名";
            }
        } catch (Exception e) {

        }
        int status = sUserDao.insertSelective(sUser);
        if (status >0){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    @Override
    public SUser login(SUser sUser) {
      SUser sUser1 = sUserDao.getByUserName(sUser.getUserName());
        try {
            if (sUser1.getPassword().equals(sUser.getPassword())){
                return sUser1;
            }else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


}
