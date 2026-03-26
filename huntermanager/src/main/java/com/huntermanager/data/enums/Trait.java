package com.huntermanager.data.enums;

public enum Trait {
    MARTIAL_ARTS("Artes Marciais", "Causa x coisa quando x coisa acontece"),
    MONSTER_HUNTER("Caçador de Monstros", "Causa x coisa quando x coisa acontece"),
    SHARPSHOOTER("Atirador Preciso", "Causa x coisa quando x coisa acontece"),
    FAST_RECOVERY("Recuperação Rápida", "Causa x coisa quando x coisa acontece"),
    NATURAL_LEADER("Líder Nato", "Causa x coisa quando x coisa acontece"),
    STEALTHY("Furtivo", "Causa x coisa quando x coisa acontece"),
    IRON_STOMACH("Estômago de Ferro", "Causa x coisa quando x coisa acontece"),
    MAGIC_SENSITIVE("Sensível à Mana", "Causa x coisa quando x coisa acontece");

    private final String displayName;
    private final String description;

    Trait(String displayName, String description) {
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