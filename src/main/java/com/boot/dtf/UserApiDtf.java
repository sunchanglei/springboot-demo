package com.boot.dtf;

import com.boot.bo.UserApiBo;
import com.boot.vo.UserApiVo;
import org.springframework.beans.BeanUtils;

/**
 * 测试表（数据转换）
 * @author yunfan.
 */
public class UserApiDtf {

    public static UserApiVo toUserApiVo(UserApiBo bo){

    	if(bo == null){
    		return null;
    	}

		UserApiVo vo = new UserApiVo();
		BeanUtils.copyProperties(vo,bo);

		return vo;
	}
}
