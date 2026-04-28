package com.huntermanager.data;

import com.huntermanager.data.enums.ItemTemplate;
import com.huntermanager.data.enums.ItemType;

public class Item {
    private String name;
    private String description;
    private ItemType type;

    public Item(String name, String description, ItemType type) {
        this.name = name;
        this.description = description;
        this.type = type;
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

// ========== TEMPLATE ==========

    public static Item fromTemplate(ItemTemplate template) {
        return new Item(
            template.getName(),
            template.getDescription(),
            template.getType()
        );
    }
}