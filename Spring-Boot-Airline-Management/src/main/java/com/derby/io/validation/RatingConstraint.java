package com.derby.io.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy=PilotRatingValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface RatingConstraint {
	String message() default "Pilot is not eligible to fly for this type of plane";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
