package com.boot.service;

import com.boot.bo.ApiBo;
import com.boot.dao.ApiDao;
import com.boot.dao.UserDao;
import com.boot.dtf.ApiDtf;
import com.boot.vo.ApiVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yunfan on 2018/7/20.
 */
@Service
public class DemoService implements IDemoService{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiDao apiDao;

    @Autowired
    private UserDao userDao;

    @Override
    public int testJdbc(){
        int count = apiDao.countByAll();

        if (count> 1){
            ApiBo bo = apiDao.findById("A001");
            if (bo == null){
                return 0;
            }
            logger.info(bo.getApiName());
        }
        return count;
    }

    @Override
    public List<ApiVo> testMybatis(){
        return ApiDtf.toApiVo(apiDao.findByApiGroup("A0"));
    }

}
