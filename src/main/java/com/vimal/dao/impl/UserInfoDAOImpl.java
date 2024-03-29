package com.vimal.dao.impl;
 
import java.util.List;
 
import javax.sql.DataSource;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vimal.dao.UserInfoDAO;
import com.vimal.model.Users;
 
@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {
 
    @Autowired
    public UserInfoDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
  
 
    @Override
    public  Users findUserInfo(String userName) {
       /* String sql = "Select u.Username,u.Password "//
                + " from Users u where u.Username = ? ";
 
        Object[] params = new Object[] { userName };
       // UserInfoMapper mapper = new UserInfoMapper();
        try {
        	Users userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return Users;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }*/
    	return null;
    }
 
 
    @Override
    public List<String> getUserRoles(String userName) {
        String sql = "Select r.User_Role "//
                + " from User_Roles r where r.Username = ? ";
         
        Object[] params = new Object[] { userName };
         
        List<String> roles = this.getJdbcTemplate().queryForList(sql,params, String.class);
         
        return roles;
    }
     
}