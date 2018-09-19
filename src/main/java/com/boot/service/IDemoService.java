package com.boot.service;

import com.boot.bo.UserBo;
import com.boot.vo.ApiVo;

import java.util.List;

/**
 * Created by yunfan on 2018/7/23.
 */
public interface IDemoService {

    int testJdbc();

    List<ApiVo> testMybatis();
}
