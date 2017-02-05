package com.vimal.dao;
 
import java.util.List;
 
import com.vimal.model.Users;
 
public interface UserInfoDAO {
     
    public Users findUserInfo(String userName);
     
    // [USER,AMIN,..]
    public List<String> getUserRoles(String userName);
     
}