package com.iitb.iitbrelay.utils.enums;

import com.iitb.iitbrelay.advice.BadRequestException;
import org.springframework.http.HttpStatus;

public enum Salutation {
    MR("Mr."),
    MS("Ms."),
    OTHER("Other");

    public final String salutation;

    Salutation(String salutation) {
        this.salutation = salutation;
    }

    public static Salutation of(String value) {
        for (Salutation salutation : Salutation.values()) {
            if (salutation.getSalutation().equals(value)) {
                return salutation;
            }
        }
        throw new BadRequestException(value + " is not a salutation", HttpStatus.NOT_FOUND.value());
    }

    public String getSalutation() {
        return salutation;
    }
}
