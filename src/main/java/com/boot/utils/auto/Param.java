package com.boot.utils.auto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Param {

    private String name;
    private String comment;
    private String type;

    public Param (String name){
        this.name=name;
        this.comment="";
        this.type="String";
    }
}
