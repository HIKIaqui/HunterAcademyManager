package com.huntermanager.data.enums;

public enum Trauma {
    MEDO_DE_ABANDONO("Medo de Abandono", "Causa x coisa quando x coisa acontece", ""),
    REFLEXO_DE_GUERRA("Reflexo de Guerra", "Causa x coisa quando x coisa acontece", ""),
    AUTODEPRECIACAO_CRONICA("Autodepreciação Crônica", "Causa x coisa quando x coisa acontece", ""),
    RAIVA_CONTIDA("Raiva Contida", "Causa x coisa quando x coisa acontece", ""),
    CULPA_PERSISTENTE("Culpa Persistente", "Causa x coisa quando x coisa acontece", ""),
    LATROFOBIA("Latrofobia", "Recebe Estresse ao invés de recuperar quando na clínica, baseado no quão baixo é o atributo Social.", "Aversão extrema e irracional a médicos."),
    ESCOLECIFOBIA("Escolecifobia", "", "Aversão extrema e irracional a vermes."),
    NICTOFOBIA("Nictofobia", "", "Aversão extrema e irracional à escuridão."),
    ONEIROFOBIA("Oneirofobia", "", "Aversão extrema e irracional a sonhos."),
    POGONOFOBIA("Pogonofobia","","Aversão extrema e irracional a barbas.");

    private final String displayName;
    private final String description;
    private final String undiscoveredDescription;
    
    Trauma(String displayName, String description, String undiscoveredDescription) {
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