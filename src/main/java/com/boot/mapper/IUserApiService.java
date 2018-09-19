package com.boot.mapper;

import com.boot.bo.UserApiBo;

import java.util.List;

public interface IUserApiService {

	/**
 	 * 查询列表。
 	 */
	public List<UserApiBo> listByParam(String param);

    /**
     * 查询明细。
     */
    public UserApiBo selectByParam(String param);

    /**
     * 查询明细。
     */
    public UserApiBo selectById(Integer id);

    /**
     * 插入数据。
     */
    public int insert(UserApiBo bo);

    /**
     * 修改数据。
     */
    public int update(UserApiBo bo);

    /**
     * 删除数据。（逻辑删除）
     */
    public int delete(UserApiBo bo);

}
