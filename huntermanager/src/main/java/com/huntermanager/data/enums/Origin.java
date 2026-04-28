package com.huntermanager.data.enums;

public enum Origin {
    AMETHYST("Ametista", "Causa x coisa quando x coisa acontece", "Nascido em Ametista"),
    CITRINE("Citrino", "Causa x coisa quando x coisa acontece", "Nascido em Citrino"),
    TURMALINA("Turmalina", "Causa x coisa quando x coisa acontece", "Nascido em Turmalina"),
    SAPPHIRE("Safira", "Causa x coisa quando x coisa acontece", "Nascido em Safira"),
    RUBY("Rubi", "Causa x coisa quando x coisa acontece", "Nascido em Rubi"),
    DIAMOND("Diamante", "Causa x coisa quando x coisa acontece", "Nascido em Diamante"),
    BISMUTH("Bismuto", "Causa x coisa quando x coisa acontece", "Nascido em Bismuto");

    private final String displayName;
    private final String description;
    private final String undiscoveredDescription;

    Origin(String displayName, String description, String undiscoveredDescription) {
        this.displayName = displayName;
        this.description = description;
        this.undiscoveredDescription = undiscoveredDescription;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getUndiscoveredDescription() {
        return undiscoveredDescription;
    }
}