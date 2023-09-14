package br.com.locatecar.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum GENDER_TYPE {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String displayName;

    GENDER_TYPE(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<String> getAllDisplayNames() {
        List<String> displayNames = new ArrayList<>();
        for (GENDER_TYPE genderType : values()) {
            displayNames.add(genderType.getDisplayName());
        }
        return displayNames;
    }
}
