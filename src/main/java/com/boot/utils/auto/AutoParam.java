package com.boot.utils.auto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AutoParam {
    private Param className;
    private Param method;
    private Param[] params;
}
