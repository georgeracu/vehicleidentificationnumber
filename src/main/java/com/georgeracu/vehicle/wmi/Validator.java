package com.georgeracu.vehicle.wmi;

import org.apache.commons.lang3.Range;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author georgicaracu
 */
class Validator {
    private final Set<Range<String>> regions =
            Arrays.stream(Region.values()).map(Region::getRegion).collect(Collectors.toSet());
    private final String value;

    private Validator(String value) {
        this.value = value;
    }

    public static Validator create(String value) {
        return new Validator(value);
    }

    public Validator validate() {
        return this.validateLength()
                .validateRegion()
                .validateCountry();
    }

    private Validator validateLength() {
        if (this.value == null ||
                this.value.isEmpty() ||
                this.value.isBlank() ||
                this.value.length() != 3) {
            throw new IllegalArgumentException("World Manufacturer Identifier needs to be 3 characters long.");
        }

        return this;
    }

    private Validator validateRegion() {
        String firstCharValue = this.value.substring(0,1);
        Set<Range<String>> ranges = regions.stream()
                .filter(r -> r.contains(firstCharValue))
                .collect(Collectors.toSet());
        if (ranges.size() == 0) {
                throw new IllegalArgumentException("Unknown region [" + firstCharValue + "]");
        } else if (ranges.size() > 1) {
            throw new IllegalArgumentException("More than one region identified [" + firstCharValue + "]");
        }
        return this;
    }

    private Validator validateCountry() {
        return this;
    }
}
