package com.boot.dtf;

import com.boot.bo.ApiBo;
import com.boot.vo.ApiVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunfan on 2018/7/23.
 */
public class ApiDtf {

    Logger logger = LoggerFactory.getLogger(getClass());

    public static List<ApiVo> toApiVo(List<ApiBo> boList){

        if(boList == null || boList.isEmpty()){
            return new ArrayList<>();
        }

        List<ApiVo>  voList = new ArrayList<>();
        boList.forEach(
            bo -> {
                ApiVo vo = new ApiVo();
                vo.setId(bo.getId());
                vo.setApiName(bo.getApiName());
                voList.add(vo);
            }
        );

        return voList;
    }
}
