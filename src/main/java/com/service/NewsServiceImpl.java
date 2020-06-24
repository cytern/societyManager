package com.service;

import com.dao.SNewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pojo.SNews;

import java.util.List;

/**
 * 新闻服务实现类
 */
@Service
public class NewsServiceImpl  implements NewsService{
    @Autowired
    private SNewsDao sNewsDao;
    public void setSNewsDao(SNewsDao sNewsDao) {
        this.sNewsDao = sNewsDao;
    }
    @Override
    public List<SNews> getNews() {
        List<SNews> sNews = sNewsDao.getAllNews();
        return sNews;
    }

    @Override
    public String addNews(SNews sNews) {
        int status = sNewsDao.insertSelective(sNews);
        if (status >0){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    @Override
    public String deleteNews(Integer id) {
        int status = sNewsDao.deleteByPrimaryKey(id);
        if (status >0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @Override
    public String updateNews(SNews sNews) {
        int status = sNewsDao.updateByPrimaryKeySelective(sNews);
        if (status >0){
            return "更新成功";
        }else {
            return "更新失败";
        }
    }


}
