package com.boot.mapper;

import com.boot.bo.UserApiBo;
import com.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserApiMapper extends BaseMapper<UserApiBo> {

    public List<UserApiBo> listByParam(@Param("param") String param);

    public UserApiBo selectByParam(@Param("param") String param);
}