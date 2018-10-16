package com.boot.utils.beancopy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BigDecimalFormat {
    int scale() default 2;
}
