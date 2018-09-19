package com.boot.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ApiVo implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String apiName;

    private String apiType;

    private String apiGroup;

    private String description;

    private BigDecimal price;



}