package com.huntermanager.data.enums;

public enum Trauma {
    MEDO_DE_ABANDONO("Medo de Abandono", "Causa x coisa quando x coisa acontece"),
    REFLEXO_DE_GUERRA("Reflexo de Guerra", "Causa x coisa quando x coisa acontece"),
    AUTODEPRECIACAO_CRONICA("Autodepreciação Crônica", "Causa x coisa quando x coisa acontece"),
    RAIVA_CONTIDA("Raiva Contida", "Causa x coisa quando x coisa acontece"),
    CULPA_PERSISTENTE("Culpa Persistente", "Causa x coisa quando x coisa acontece");

    private final String displayName;
    private final String description;
    Trauma(String displayName, String description) {
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