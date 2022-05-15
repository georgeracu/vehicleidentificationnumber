package com.georgeracu.vehicle.wmi;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Author georgicaracu
 */
class ValidatorTest {

    @ParameterizedTest
    @ValueSource( strings = {"", " ", "ABCDEF0123456789", "ABCDEF012345678901", "                 "})
    void shouldValidateLength(String source) {
        assertThatThrownBy(() -> Validator.create(source).validate())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource( strings = {"AA1", "JT1", "JF1", "SA1", "1AA", "2AA", "7FA", "70A", "6AA", "7EE", "8AA", "8BA", "9BB"})
    void shouldValidate(String source) {
        Validator.create(source).validate();
    }

}