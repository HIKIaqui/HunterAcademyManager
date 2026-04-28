package com.huntermanager.data.itemTypes;

import com.huntermanager.data.Item;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.ItemType;
import com.huntermanager.data.itemTypes.itemData.Usable;

public class Consumable extends Item implements Usable {
    private final int healingAmount;

    public Consumable(String name, String description, int healingAmount) {
        super(name, description, ItemType.CONSUMABLE);
        this.healingAmount = healingAmount;
    }

    @Override
    public void use(MonsterHunter hunter) {
        hunter.heal(healingAmount);
    }
}