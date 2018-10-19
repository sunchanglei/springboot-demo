package com.boot.service;

import com.boot.vo.ApiVo;
import com.boot.comm.RequestMap;
import com.boot.comm.ResponseMap;

import java.util.List;

/**
 * Created by yunfan on 2018/7/23.
 */
public interface IDemoService {

    int testJdbc();

    List<ApiVo> testMybatis();

    ApiVo testComm(String id);

    ResponseMap listByAll(RequestMap requestMap) throws Exception;
}
