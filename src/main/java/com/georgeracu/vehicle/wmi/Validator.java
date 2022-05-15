package com.georgeracu.vehicle.wmi;

import org.apache.commons.lang3.Range;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author georgicaracu
 */
class Validator {
    private static final Range<String> regionAfrica = Range.between("A", "H");
    private static final Range<String> regionAsia = Range.between("J", "R");
    private static final Range<String> regionEurope = Range.between("S", "Z");
    private static final Range<String> regionNorthAmerica_1 = Range.between("1", "5");
    private static final Range<String> regionNorthAmerica_2 = Range.between("7F", "70");
    private static final Range<String> regionOceania = Range.between("6", "7E");
    private static final Range<String> regionSouthAmerica = Range.between("8", "9");
    private static final Set<Range<String>> regions = Set.of(regionAfrica, regionAsia, regionEurope, regionNorthAmerica_1,
            regionNorthAmerica_2, regionOceania, regionSouthAmerica);
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
