package com.georgeracu.vehicle;

import com.georgeracu.vehicle.wmi.WorldManufacturerIdentifier;

/**
 * Author georgicaracu
 */
public record VehicleIdentificationNumber(String value) {
    public VehicleIdentificationNumber {
        Validator.create(value).validateLength();
    }

    public WorldManufacturerIdentifier worldManufacturerIdentifier() {
        return new WorldManufacturerIdentifier(this.value.substring(0, 3));
    }

    public VehicleDescriptor vehicleDescriptor() {
        return new VehicleDescriptor(this.value.substring(3, 9));
    }

    public VehicleIdentifier vehicleIdentifier() {
        return new VehicleIdentifier(this.value.substring(9));
    }
}

final class Validator {
    private static final int ALLOWED_LENGTH = 17;
    private final String value;

    private Validator(String value) {
        this.value = value;
    }

    public static Validator create(String value) {
        return new Validator(value);
    }

    public Validator validateLength() {
        if (value == null || value.isBlank() || value.length() != ALLOWED_LENGTH) {
            throw new VehicleIdentificationNumberException("Value for VIN must be 17 characters long");
        }
        return this;
    }

}
