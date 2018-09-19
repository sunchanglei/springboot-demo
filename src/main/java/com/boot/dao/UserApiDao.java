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
public class UserApiDao {

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

    /**
     * 查询明细。
     */
    public UserApiBo selectById(Integer id) {
    	return userApiMapper.selectByPrimaryKey(id);
    }

	/**
	 * 插入数据。
	 */
    public int insert(UserApiBo bo) {
    	return userApiMapper.insertSelective(bo);
	}

    /**
     * 修改数据。
     */
    public int update(UserApiBo bo) {
    	return userApiMapper.updateByPrimaryKeySelective(bo);
	}

    /**
     * 删除数据。（逻辑删除）
     */
    public int delete(UserApiBo bo) {
    	return userApiMapper.updateByPrimaryKeySelective(bo);
	}

}
