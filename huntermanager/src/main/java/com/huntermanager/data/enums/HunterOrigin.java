package com.huntermanager.data.enums;

public enum HunterOrigin {
    AMETISTA("Ametista", "Causa x coisa quando x coisa acontece"),
    SAFIRA("Safira", "Causa x coisa quando x coisa acontece"),
    CITRINO("Citrino", "Causa x coisa quando x coisa acontece"),
    RUBI("Rubi", "Causa x coisa quando x coisa acontece"),
    DIAMANTE("Diamante", "Causa x coisa quando x coisa acontece"),
    TURMALINA("Turmalina", "Causa x coisa quando x coisa acontece");

    private final String displayName;
    private final String description;
    
    HunterOrigin(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}