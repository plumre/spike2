package com.plumre.validator;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 9:29
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    public ValidationResult validate(Object object) {
        final ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> violationSet = validator.validate(object);
        if (violationSet.size() > 0) {
            // has errors
            result.setHasErrors(true);
            violationSet.forEach(violation -> {
                String message = violation.getMessage();
                String property = violation.getPropertyPath().toString();
                result.getErrorMessageMap().put(property, message);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // instancing validator in Hibernate via factory style
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}