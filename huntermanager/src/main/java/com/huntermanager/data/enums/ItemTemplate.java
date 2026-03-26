package com.huntermanager.data.enums;

public enum ItemTemplate {

    // ===== ARMAS =====
    IRON_SWORD("Espada de Ferro", "Uma espada básica.", ItemType.WEAPON),
    HUNTER_DAGGER("Adaga de Caçador", "Rápida e precisa.", ItemType.WEAPON),

    // ===== ARMADURAS =====
    LEATHER_ARMOR("Armadura de Couro", "Leve e flexível.", ItemType.ARMOR),
    IRON_ARMOR("Armadura de Ferro", "Pesada, mas resistente.", ItemType.ARMOR),

    // ===== ACESSÓRIOS =====
    LUCKY_CHARM("Amuleto da Sorte", "Talvez funcione.", ItemType.ACCESSORY),

    // ===== CONSUMÍVEIS =====
    BASIC_MEDKIT("Kit Médico Básico", "Recupera vida.", ItemType.CONSUMABLE);

    private final String name;
    private final String description;
    private final ItemType type;

    ItemTemplate(String name, String description, ItemType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }
}