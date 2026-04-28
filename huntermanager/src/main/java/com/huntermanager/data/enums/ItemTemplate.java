package com.huntermanager.data.enums;

import java.util.List;

import com.huntermanager.data.Item;
import com.huntermanager.data.itemTypes.Accessory;
import com.huntermanager.data.itemTypes.Armor;
import com.huntermanager.data.itemTypes.Consumable;
import com.huntermanager.data.itemTypes.Weapon;
import com.huntermanager.data.itemTypes.itemData.StatsModifier;

public enum ItemTemplate {

    // ===== ARMAS =====
    RUSTY_SWORD(
        "Espada Enferrujada",
        "Uma espada velha, mas ainda corta.",
        ItemType.WEAPON,
        EquipmentSlot.WEAPON,
        List.of(
            new StatsModifier(Stats.DAMAGE, 4),
            new StatsModifier(Stats.CRIT_CHANCE, 2)
        ),
        0
    ),

    HEAVY_ARMOR(
        "Armadura Pesada",
        "Protege bem, mas te transforma numa geladeira com depressão.",
        ItemType.ARMOR,
        EquipmentSlot.ARMOR,
        List.of(
            new StatsModifier(Stats.PROTECTION, 6),
            new StatsModifier(Stats.DODGE, -2),
            new StatsModifier(Stats.SPEED, -1)
        ),
        0
    ),

    SMALL_MEDICINE(
        "Remédio Pequeno",
        "Recupera um pouco de vida.",
        ItemType.CONSUMABLE,
        null,
        List.of(),
        10
    );

    private final String name;
    private final String description;
    private final ItemType type;
    private final EquipmentSlot slot;
    private final List<StatsModifier> modifiers;
    private final int healingAmount;

    ItemTemplate(
        String name,
        String description,
        ItemType type,
        EquipmentSlot slot,
        List<StatsModifier> modifiers,
        int healingAmount
    ) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.slot = slot;
        this.modifiers = modifiers;
        this.healingAmount = healingAmount;
    }

    public Item createItem() {
        return switch (type) {
            case ACCESSORY -> new Accessory(name, description, modifiers);
            case WEAPON -> new Weapon(name, description, modifiers);
            case ARMOR -> new Armor(name, description, modifiers);
            case CONSUMABLE -> new Consumable(name, description, healingAmount);
        };
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

    public EquipmentSlot getSlot() {
        return slot;
    }

    public List<StatsModifier> getModifiers() {
        return modifiers;
    }

    public int getHealingAmount() {
        return healingAmount;
    }
}