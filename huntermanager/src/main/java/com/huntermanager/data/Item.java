package com.huntermanager.data;

import com.huntermanager.data.enums.ItemTemplate;
import com.huntermanager.data.enums.ItemType;

public class Item {
    private String name;
    private String description;
    private ItemType type;
    private MonsterHunter equippedBy;

    public Item(String name, String description, ItemType type) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.equippedBy = null;
    }

// ========== GETTERS ==========

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public MonsterHunter getEquippedBy() {
        return equippedBy;
    }

// ========== EQUIP ==========

    public boolean isEquipped() {
        return equippedBy != null;
    }

    public void setEquippedBy(MonsterHunter equippedBy) {
        this.equippedBy = equippedBy;
    }

// ========== TEMPLATE ==========

    public static Item fromTemplate(ItemTemplate template) {
        return new Item(
            template.getName(),
            template.getDescription(),
            template.getType()
        );
    }
}