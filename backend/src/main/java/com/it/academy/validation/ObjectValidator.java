package com.it.academy.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public Set<String> validate(T object) {
        Set<ConstraintViolation<T>> violation = validator.validate(object);
        if (!violation.isEmpty()) {
           return violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
        }
        return Collections.EMPTY_SET;
    }
}
