package com.boot.dao;

import com.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yunfan on 2018/7/23.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate ysJdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    public int countByAll(){
        List<Map<String,Object>> list = ysJdbcTemplate.queryForList("select COUNT(*) as num from user");
        if (list == null || list.isEmpty()){
            return 0;
        }

        return Integer.parseInt(String.valueOf(list.get(0).get("num")));
    }
}
