package com.boot.dao;

import com.boot.bo.ApiBo;
import com.boot.mapper.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yunfun on 2018/7/23.
 */
@Repository
public class ApiDao extends CommDao {

    @Autowired
    private JdbcTemplate yhJdbcTemplate;

    @Autowired
    private ApiMapper apiMapper;

    public int countByAll(){
        List<Map<String,Object>> list = yhJdbcTemplate.queryForList("select COUNT(*) as num from api");
        if (list == null || list.isEmpty()){
            return 0;
        }

        return Integer.parseInt(String.valueOf(list.get(0).get("num")));
    }

    public ApiBo findById(String id){
        List<Map<String,Object>> list = yhJdbcTemplate.queryForList("select * from api where id = ?",id);
        if (list == null || list.isEmpty()){
            return null;
        }

        ApiBo bo = new ApiBo();
        list.forEach(map -> {
            bo.setId((String)map.get("id"));
            bo.setApiName((String)map.get("api_name"));
        });

        return bo;
    }

    public List<ApiBo> findByApiGroup(String apiGroup){

//        DatabaseContextHolder.setDatabaseType(DatabaseType.mytestdb);
        return apiMapper.selectByApiGroup(apiGroup);
    }
}
