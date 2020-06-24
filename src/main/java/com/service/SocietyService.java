package com.service;


import com.pojo.BackSociety;
import com.pojo.Society;

import java.util.List;

/**
 * 接口类，社团服务接口
 */
public interface SocietyService {
    List<BackSociety> getSocieties();
    String updateSocieties(Society society);
    String deleteSocieties(Integer id);
    String addSocieties(Society society);

    List<Society> getAllS();
}
