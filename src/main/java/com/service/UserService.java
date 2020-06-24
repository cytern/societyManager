package com.service;


import com.pojo.SUser;

import java.util.List;

public interface UserService {
   List<SUser> getAllUser();
   String deleteUser(Integer id);
   String updateUser(SUser sUser);
   String addUser(SUser sUser);
   SUser login(SUser sUser);
}
