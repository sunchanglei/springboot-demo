package com.boot.mapper;

import com.boot.bo.ApiBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiMapper extends BaseMapper<ApiBo>{


    List<ApiBo> selectByApiGroup(@Param("apiGroup") String apiGroup);
}