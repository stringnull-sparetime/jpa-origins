package org.stringnull.core.data.query.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JPAOriginsColumn {
    String name() default "";
    boolean nullable() default true;
    int length() default 0;
}
