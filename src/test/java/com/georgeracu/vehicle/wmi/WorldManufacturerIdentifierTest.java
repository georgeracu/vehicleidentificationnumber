package com.georgeracu.vehicle.wmi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author georgicaracu
 */
class WorldManufacturerIdentifierTest {

    @ParameterizedTest
    @CsvSource({
            "'AAA','AFRICA'",
            "'JT1','ASIA'",
            "'7FA','NORTH_AMERICA_2'",
            "'70A','NORTH_AMERICA_2'",
            "'6AA','OCEANIA'",
            "'7EE','OCEANIA'",
            "'8AA','SOUTH_AMERICA'",
            "'8BA','SOUTH_AMERICA'",
            "'9BB','SOUTH_AMERICA'"})
    void shouldCreateWMI(String input, String regionString) {
        Region expected = Region.valueOf(regionString);
        WorldManufacturerIdentifier wmi = new WorldManufacturerIdentifier(input);

        Region actual = wmi.region();

        // assert
        assertThat(actual).isEqualTo(expected);
    }

}