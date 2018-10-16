package com.boot.utils.auto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Method {

    private String type;
    private String name;
    private String comment;
    private String paramStr;
    private String paramTypeStr;
    private Param[] params;
    private String retName;
    private String retType;
    private String retDesc;

}
