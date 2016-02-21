package co.com.sigepro.persistencia.util;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;



@ValidatorClass(NotWhiteSpaceValidator.class)
@Target( ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotWhiteSpace {

	String message();
}