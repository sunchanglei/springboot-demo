package com.boot.service;

import com.boot.bo.ApiBo;
import com.boot.dao.ApiDao;
import com.boot.dao.UserDao;
import com.boot.dtf.ApiDtf;
import com.boot.exception.BizException;
import com.boot.vo.ApiVo;
import com.boot.comm.RequestMap;
import com.boot.comm.ResponseMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<ApiBo> boList = apiDao.findByApiGroup("A0");
        if(boList == null || boList.isEmpty()){
            return new ArrayList<>();
        }

        List<ApiVo>  voList = new ArrayList<>();
        for (ApiBo bo : boList) {
            ApiVo vo = ApiDtf.toApiVo(bo);
            voList.add(vo);
        }

        return voList;
    }

    @Override
    public ResponseMap listByAll(RequestMap requestMap) throws Exception{

        // 参数校验：
        String api = requestMap.getStringValueRequired("接口号", "api");

        // 数据查询：
        List<ApiBo> boList = apiDao.findByApiGroup(api);
        if(boList == null || boList.isEmpty()){
            return new ResponseMap();
        }

        // 数据解析：
        List<ApiVo>  voList = new ArrayList<>();
        for (ApiBo bo : boList) {
            ApiVo vo = ApiDtf.toApiVo(bo);
            voList.add(vo);
        }

        // 结果返回：
        ResponseMap resMap = new ResponseMap();
        resMap.put("apiList",voList);
        return resMap;
    }

}
