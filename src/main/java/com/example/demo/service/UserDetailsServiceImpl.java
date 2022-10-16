package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dao.ListValuesDAO;
import com.example.demo.dao.ListValuesDAOImpl;
import com.example.demo.dao.ObjectDAO;
import com.example.demo.dao.ParamsDAO;
import com.example.demo.entity.ListValues;
import com.example.demo.entity.Objects;
import com.example.demo.entity.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ObjectDAO objectDAO;

    @Autowired
    private ParamsDAO paramsDAO;

    @Autowired
    private ListValuesDAOImpl listValuesDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Objects objects = this.objectDAO.findUserAccount(userName);

        if (objects == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        System.out.println("Found User: " + objects.getName());

        Params password = this.paramsDAO.getValue(objects.getObject_id(),11L);
        Params roleId = this.paramsDAO.getValue(objects.getObject_id(),12L);
        ListValues roleName = this.listValuesDAO.findById(roleId.getList_value_id()).orElseThrow();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName.getName());
        grantList.add(authority);
        return (UserDetails) new User(objects.getName(), password.getValue(), grantList);
    }
}