package com.georgeracu.vehicle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Author georgicaracu
 */
class VehicleIdentificationNumberTest {

    @ParameterizedTest(name = "Value [{0}] should throw an exception")
    @ValueSource( strings = {"", " ", "ABCDEF0123456789", "ABCDEF012345678901", "                 "})
    void shouldNotCreateObjectWhenLengthNotThirteenCharacters(String input) {
        assertThatThrownBy(() -> new VehicleIdentificationNumber(input))
                .isInstanceOf(VehicleIdentificationNumberException.class);
    }

    @Test
    void shouldReturnWorldManufacturerIdentifier() {
        // arrange
        final var vin = new VehicleIdentificationNumber("AAABBBBBBEFF12345");

        // assert
        assertThat(vin.worldManufacturerIdentifier()).isEqualTo("AAA");
    }

    @Test
    void shouldReturnVehicleDescriptor() {
        // arrange
        final var vin = new VehicleIdentificationNumber("AAABBBBBBEFF12345");

        // assert
        assertThat(vin.vehicleDescriptor()).isEqualTo("BBBBBB");
    }

    @Test
    void shouldReturnVehicleIdentifier() {
        // arrange
        final var vin = new VehicleIdentificationNumber("AAABBBBBB01234567");

        // assert
        assertThat(vin.vehicleIdentifier()).isEqualTo("01234567");
    }
}