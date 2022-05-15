package com.georgeracu.vehicle.wmi;

import org.apache.commons.lang3.Range;

/**
 * Author georgicaracu
 */
public enum Region {
    AFRICA(Range.between("A", "H")),
    ASIA(Range.between("J", "R")),
    EUROPE(Range.between("S", "Z")),
    NORTH_AMERICA_1(Range.between("1", "5")),
    NORTH_AMERICA_2(Range.between("7F", "70")),
    SOUTH_AMERICA(Range.between("8", "9")),
    OCEANIA(Range.between("6", "7E"));
    private final Range<String> region;

    Region(Range<String> region) {
        this.region = region;
    }

    public Range<String> getRegion() {
        return region;
    }

    public static Region ofRegionCode(String input) {
        for(Region r: Region.values()) {
            if (r.region.contains(input)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Cannot find Region with input [" + input + "]");
    }
}
