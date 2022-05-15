package com.georgeracu.vehicle.wmi;

import org.apache.commons.lang3.Range;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author georgicaracu
 */
public record WorldManufacturerIdentifier(String value) {

    public Region region() {
        String firstCharValue = this.value.substring(0,1);
        Set<Range<String>> regions =
                Arrays.stream(Region.values()).map(Region::getRegion).collect(Collectors.toSet());
        Set<Range<String>> ranges = regions.stream()
                .filter(r -> r.contains(firstCharValue))
                .collect(Collectors.toSet());
        if (ranges.size() == 0) {
            throw new IllegalArgumentException("Unknown region [" + firstCharValue + "]");
        } else if (ranges.size() > 1) {
            throw new IllegalArgumentException("More than one region identified [" + firstCharValue + "]");
        } else {
            return Region.ofRegionCode(firstCharValue);
        }
    }
}
