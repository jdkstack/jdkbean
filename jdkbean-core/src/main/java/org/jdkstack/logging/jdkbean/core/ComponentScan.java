package org.jdkstack.logging.jdkbean.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScan {

  String[] value() default {};

  Filter[] includeFilters() default {};

  Filter[] excludeFilters() default {};
}
