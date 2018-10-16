package com.boot.dao;

import com.boot.bo.UserApiBo;
import com.boot.mapper.UserApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试表（数据层）
 */
@Repository
public class UserApiDao extends CommDao{

	@Autowired
    private UserApiMapper userApiMapper;

	/**
 	 * 查询列表。
 	 */
	public List<UserApiBo> listByParam(String param) {
		return userApiMapper.listByParam(param);
	}

    /**
     * 查询明细。
     */
    public UserApiBo selectByParam(String param) {
		return userApiMapper.selectByParam(param);
	}
}
