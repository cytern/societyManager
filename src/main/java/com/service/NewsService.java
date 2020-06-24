package com.service;

import com.pojo.SNews;

import java.util.List;

/**
 * 新闻服务接口
 */
public interface NewsService {
    List<SNews> getNews();
    String addNews(SNews sNews);
    String deleteNews(Integer id);
    String updateNews(SNews sNews);
}
