package com.boot.dtf;

import com.boot.bo.ApiBo;
import com.boot.utils.BeanUtil;
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

    public static ApiVo toApiVo(ApiBo bo){

        ApiVo vo = new ApiVo();
        BeanUtil.copy(vo,bo);
        return vo;
    }
}
