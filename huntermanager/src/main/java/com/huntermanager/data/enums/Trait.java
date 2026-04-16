package com.huntermanager.data.enums;

public enum Trait {
    MARTIAL_ARTS("Artes Marciais", "Causa x coisa quando x coisa acontece", ""),
    MONSTER_HUNTER("Caçador de Monstros", "Causa x coisa quando x coisa acontece", ""),
    SHARPSHOOTER("Atirador Preciso", "Causa x coisa quando x coisa acontece", ""),
    FAST_RECOVERY("Recuperação Rápida", "A recuperação de PV na clínica é dobrada.", "Se recupera de feridas mais rápido que a maioria."),
    NATURAL_LEADER("Líder Nato", "Causa x coisa quando x coisa acontece", ""),
    STEALTHY("Furtivo", "Causa x coisa quando x coisa acontece", ""),
    IRON_STOMACH("Estômago de Ferro", "Causa x coisa quando x coisa acontece", ""),
    MAGIC_SENSITIVE("Sensível à Mana", "Causa x coisa quando x coisa acontece", "");

    private final String displayName;
    private final String description;
    private final String undiscoveredDescription;

    Trait(String displayName, String description, String undiscoveredDescription) {
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